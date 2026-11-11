package owenparker.gui.grid;

import owenparker.engine.BorderPolicy;
import owenparker.engine.Bounds;
import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;

public class GridDisplay extends AccessorComponent<JPanel> {
    private static final double ZOOM_AMOUNT = .1;
    private static final double MIN_SCALE = 1;
    private static final double MAX_SCALE = 300;
    private static final double DEFAULT_CELL_SCALE = 20;

	private Bounds boundsToRender = null;

    private double cellScale;
    public BigDecimal scrollX;
    public BigDecimal scrollY;

	private MouseEvent lastMouseEvent = null;

    public double getCellScale() {
        return this.cellScale;
    }

	public void resetCamera() {
		this.cellScale = DEFAULT_CELL_SCALE;
		this.scrollX = BigDecimal.ZERO;
		this.scrollY = BigDecimal.ZERO;
	}

	public void renderBounds(Bounds bounds) {
		this.boundsToRender = bounds;
		this.globalReference.repaintEvent.fire();
	}

	public OrderedPair getCursorLocationInCellSpace() {
		if (this.lastMouseEvent == null)
			return null;
		return fromScreenSpaceToCellSpace(new OrderedPair(this.lastMouseEvent));
	}

	public void setCamera(BigInteger x, BigInteger y) {
		this.scrollX = new BigDecimal(x).multiply(BigDecimal.valueOf(this.cellScale)).negate();
		this.scrollY = new BigDecimal(y).multiply(BigDecimal.valueOf(this.cellScale)).negate();
	}

	public void moveCamera(OrderedPair difference) {
		this.scrollX = this.scrollX.add(new BigDecimal(difference.x()));
		this.scrollY = this.scrollY.add(new BigDecimal(difference.y()));
	}

	private class GridDisplayPanel extends JPanel {
        public GridDisplayPanel() {
			resetCamera();

			setBorder(new LineBorder(GridDisplay.this.globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES), 1, false));

            GridDisplay.this.globalReference.repaintEvent.add(() -> {
                revalidate();
                repaint();
            });

			GridDisplay.this.globalReference.cursorModeChangeEvent.add(this::refreshListeners);
			GridDisplay.this.globalReference.cursorModeChangeEvent.add(GridDisplay.this.globalReference.repaintEvent::fire);
			refreshListeners();
        }

		private void refreshListeners() {
			for (MouseListener mouseListener : getMouseListeners()) {
				removeMouseListener(mouseListener);
			}
			for (MouseMotionListener mouseMotionListener : getMouseMotionListeners()) {
				removeMouseMotionListener(mouseMotionListener);
			}
			for (MouseWheelListener mouseWheelListener : getMouseWheelListeners()) {
				removeMouseWheelListener(mouseWheelListener);
			}
			
			CursorModeOperation operation = switch (GridDisplay.this.globalReference.getCurrentCursorMode()) {
				case SELECT -> new SelectOperation(GridDisplay.this);
				case PENCIL -> new PencilOperation(GridDisplay.this);
				case RECTANGLE -> new FillRectangleOperation(GridDisplay.this);
			};
			
			addMouseListener(operation);
			addMouseMotionListener(operation);
			addMouseWheelListener(operation);

			addMouseMotionListener(new MouseInputAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					super.mouseMoved(e);
					GridDisplay.this.globalReference.mouseMoveEvent.fire();
					GridDisplay.this.lastMouseEvent = e;
				}
			});
		}

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            fillBackgroundWithDefaultCellColor(g);
            drawAllCells(g);
            drawAllAgents(g);
            drawGridlinesIfApplicable(g);
            drawBordersIfApplicable(g);
			drawBounds(g);
        }
    }

	private void drawBounds(Graphics g) {
		if (this.boundsToRender == null)
			return;

		Bounds bounds = fromCellSpaceToScreenSpace(this.boundsToRender);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(this.globalReference.getColorPalette().getColor(ColorPalette.Slot.ACTIVE_2));
		g2d.setStroke(new BasicStroke(4));

		BigInteger top    = bounds.getTopLeft().y();
		BigInteger bottom = bounds.getBottomRight().y();
		BigInteger left   = bounds.getTopLeft().x();
		BigInteger right  = bounds.getBottomRight().x();

		g2d.drawRect(
				left.intValue(),
				top.intValue(),
				right.subtract(left).add(BigInteger.valueOf((long) this.cellScale)).intValue(),
				bottom.subtract(top).add(BigInteger.valueOf((long) this.cellScale)).intValue()
		);
	}

	private void drawBordersIfApplicable(Graphics g) {
        if (!globalReference.getAutomaton().getConfig().borderPolicy.get().equals(BorderPolicy.FILL_WITH_DEFAULT))
            return;

        OrderedPair topLeft = fromCellSpaceToScreenSpace(new OrderedPair(0, 0));
        OrderedPair bottomRight = fromCellSpaceToScreenSpace(globalReference.getAutomaton().getConfig().getSize());

        OrderedPair screenSize = new OrderedPair(getComponent().getSize());

        g.setColor(globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));

        g.fillRect(0, 0, screenSize.x().intValue(), topLeft.y().intValue()); // Top
        g.fillRect(0, bottomRight.y().intValue(), screenSize.x().intValue(), screenSize.y().subtract(bottomRight.y()).intValue()); // Bottom
        g.fillRect(0, 0, topLeft.x().intValue(), screenSize.y().intValue()); // Left
        g.fillRect(bottomRight.x().intValue(), 0, screenSize.x().subtract(bottomRight.x()).intValue(), screenSize.y().intValue()); // Right
    }

    public GridDisplay(GlobalReference globalReference) {
        super(globalReference);
        setComponent(new GridDisplayPanel());
        globalReference.gridDisplay = this;
		globalReference.cursorModeChangeEvent.add(() -> this.boundsToRender = null);
    }

    public void performZoom(MouseWheelEvent event) {
        BigDecimal modifier = new BigDecimal(1 + -ZOOM_AMOUNT * event.getPreciseWheelRotation());

		OrderedPair location = new OrderedPair(event);

		BigDecimal cellScale = new BigDecimal(this.cellScale);

        BigDecimal tentativeNewScale = cellScale.multiply(modifier);

        if (tentativeNewScale.compareTo(new BigDecimal(MIN_SCALE)) < 0 || tentativeNewScale.compareTo(new BigDecimal(MAX_SCALE)) > 0)
            return;

        OrderedPair mousePosOnScreen = new OrderedPair(location.x(), location.y());

        int width = getComponent().getWidth();
        int height = getComponent().getHeight();

        //this.scrollX = this.scrollX * modifier - (mousePosOnScreen.x().doubleValue() / width * (width * modifier - width));
        //this.scrollY = this.scrollY * modifier - (mousePosOnScreen.y().doubleValue() / height * (height * modifier - height));

		this.scrollX = this.scrollX.multiply(modifier).subtract(new BigDecimal(mousePosOnScreen.x().doubleValue() / width * (width * modifier.doubleValue() - width)));
		this.scrollY = this.scrollY.multiply(modifier).subtract(new BigDecimal(mousePosOnScreen.y().doubleValue() / height * (height * modifier.doubleValue() - height)));

        this.cellScale = tentativeNewScale.doubleValue();
    }

    private void drawGridlinesIfApplicable(Graphics g) {
        if (GridDisplay.this.cellScale < this.globalReference.preferences.cellScaleToStopDrawingGridlines.get())
            return;

        int sizeOfLines = (int) Math.clamp(4 + (30 / this.cellScale), 0, 3);

        OrderedPair screenSizeInPixels = getScreenSizeInPixels();
        g.setColor(this.globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));
        for (double x = this.scrollX.doubleValue() % this.cellScale; x < screenSizeInPixels.x().intValue(); x += this.cellScale)
            g.fillRect((int) x - 1, 0, sizeOfLines, screenSizeInPixels.y().intValue());
        for (double y = this.scrollY.doubleValue() % this.cellScale; y < screenSizeInPixels.y().intValue(); y += this.cellScale)
            g.fillRect(0, (int) y - 1, screenSizeInPixels.x().intValue(), sizeOfLines);
    }

    private void drawAllAgents(Graphics g) {
		for (OrderedPair location : getCellLocationsOnScreen()) {
			this.globalReference.getAutomaton().findAgentsAt(location)
					.forEach(agent -> {
						Bounds bounds = new Bounds(location.scale(this.cellScale).add(new OrderedPair(this.scrollX, this.scrollY)), new OrderedPair((int) this.cellScale, (int) this.cellScale));

						OrderedPair screenSpace = fromCellSpaceToScreenSpace(location);

						agent.draw(g.create(
								screenSpace.x().intValue(),
								screenSpace.y().intValue(),
								(int) this.cellScale,
								(int) this.cellScale
						));
					});
		}
    }

    private void drawAllCells(Graphics g) {
        for (OrderedPair location : getCellLocationsOnScreen())
            drawCellAt(g, location);
    }

    private void drawCellAt(Graphics g, OrderedPair location) {
        State state = this.globalReference.getAutomaton().getCellAt(location);
        if (isDefaultState(state)) return;
        g.setColor(state.getColorEvaluator().getColor(this.globalReference.getColorPalette()));
        OrderedPair locationInScreenSpace = fromCellSpaceToScreenSpace(location);
        int cellSize = (int) this.cellScale + 1; // 1 is added to prevent gaps
        g.fillRect(locationInScreenSpace.x().intValue(), locationInScreenSpace.y().intValue(), cellSize, cellSize);
        state.customDraw(g.create(locationInScreenSpace.x().intValue(), locationInScreenSpace.y().intValue(), cellSize, cellSize));

		if (this.globalReference.preferences.debug.get().drawUpdater.get() && this.globalReference.getAutomaton().isToBeUpdated(location)) {
			g.setColor(Color.YELLOW);
			g.fillRect(locationInScreenSpace.x().intValue(), locationInScreenSpace.y().intValue(), cellSize / 2, cellSize / 2);
		}
    }

    private OrderedPair fromCellSpaceToScreenSpace(OrderedPair cellSpace) {
        return cellSpace
                .scale(this.cellScale)
                .add(new OrderedPair(this.scrollX.toBigInteger(), this.scrollY.toBigInteger()));
    }

	private Bounds fromCellSpaceToScreenSpace(Bounds bounds) {
		return new Bounds(fromCellSpaceToScreenSpace(bounds.a()), fromCellSpaceToScreenSpace(bounds.b()));
	}

    public OrderedPair fromScreenSpaceToCellSpace(OrderedPair screenSpace) {
		return screenSpace
                .subtract(new OrderedPair(this.scrollX.toBigInteger(), this.scrollY.toBigInteger()))
				.divide(this.cellScale);
    }

	public Bounds fromScreenSpaceToCellSpace(Bounds bounds) {
		return new Bounds(fromScreenSpaceToCellSpace(bounds.a()), fromScreenSpaceToCellSpace(bounds.b()));
	}

    private boolean isDefaultState(State state) {
        return this.globalReference.getAutomaton().getDefaultState().equals(state);
    }

    public Collection<OrderedPair> getCellLocationsOnScreen() {
        OrderedPair scrollAsCells = new OrderedPair(this.scrollX, this.scrollY).negate().divide(this.cellScale);
        OrderedPair screenSizeInCells = getScreenSizeInPixels().divide(this.cellScale);

        Collection<OrderedPair> locations = new LinkedList<>();
        for (BigInteger y = scrollAsCells.y().subtract(BigInteger.ONE); y.compareTo(scrollAsCells.y().add(screenSizeInCells.y().add(BigInteger.ONE))) <= 0; y = y.add(BigInteger.ONE)) {
            for (BigInteger x = scrollAsCells.x().subtract(BigInteger.ONE); x.compareTo(scrollAsCells.x().add(screenSizeInCells.x().add(BigInteger.ONE))) <= 0; x = x.add(BigInteger.ONE)) {
                locations.add(new OrderedPair(x, y));
            }
        }

        return locations;
    }

    private void fillBackgroundWithDefaultCellColor(Graphics g) {
        OrderedPair screenSizeInPixels = getScreenSizeInPixels();
        g.setColor(this.globalReference.getAutomaton().getDefaultState().getColorEvaluator().getColor(this.globalReference.getColorPalette()));
        g.fillRect(0, 0, screenSizeInPixels.x().intValue(), screenSizeInPixels.y().intValue());
    }

    private OrderedPair getScreenSizeInPixels() {
        Dimension dimension = getComponent().getSize();
        return new OrderedPair(dimension.width, dimension.height);
    }
}

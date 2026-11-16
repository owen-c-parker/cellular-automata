package owenparker.gui.grid;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;
import owenparker.gui.preferences.Preferences;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;

public class GridDisplay extends JPanel {
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
		Events.repaint.fire();
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

		CursorModeOperation operation = switch (DisplayContext.getCurrentCursorMode()) {
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
				Events.mouseMove.fire();
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

	private void drawBounds(Graphics g) {
		if (this.boundsToRender == null)
			return;

		Bounds bounds = fromCellSpaceToScreenSpace(this.boundsToRender);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(DisplayContext.getColorPalette().getColor(ColorPalette.Slot.ACTIVE_2));
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
		AutomatonConfig config = SimulationContext.getAutomaton().getConfig();

        if (!config.borderPolicy.get().equals(BorderPolicy.FILL_WITH_DEFAULT))
            return;

        OrderedPair topLeft = fromCellSpaceToScreenSpace(new OrderedPair(0, 0));
        OrderedPair bottomRight = fromCellSpaceToScreenSpace(config.getSize());

        OrderedPair screenSize = new OrderedPair(getSize());

        g.setColor(DisplayContext.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));

        g.fillRect(0, 0, screenSize.x().intValue(), topLeft.y().intValue()); // Top
        g.fillRect(0, bottomRight.y().intValue(), screenSize.x().intValue(), screenSize.y().subtract(bottomRight.y()).intValue()); // Bottom
        g.fillRect(0, 0, topLeft.x().intValue(), screenSize.y().intValue()); // Left
        g.fillRect(bottomRight.x().intValue(), 0, screenSize.x().subtract(bottomRight.x()).intValue(), screenSize.y().intValue()); // Right
    }

    public GridDisplay() {
		super();

        resetCamera();

		setBorder(new LineBorder(DisplayContext.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES), 1, false));

		Events.repaint.add(() -> {
			revalidate();
			repaint();
		});

		Events.cursorModeChange.add(this::refreshListeners);
		Events.cursorModeChange.add(Events.repaint::fire);
		refreshListeners();

        DisplayContext.gridDisplay = this;
		Events.cursorModeChange.add(() -> this.boundsToRender = null);
		Preferences.getInstance().gridlineType.addSetListener(Events.repaint::fire);
    }

    public void performZoom(MouseWheelEvent event) {
        BigDecimal modifier = new BigDecimal(1 + -ZOOM_AMOUNT * event.getPreciseWheelRotation());

		OrderedPair location = new OrderedPair(event);

		BigDecimal cellScale = new BigDecimal(this.cellScale);

        BigDecimal tentativeNewScale = cellScale.multiply(modifier);

        if (tentativeNewScale.compareTo(new BigDecimal(MIN_SCALE)) < 0 || tentativeNewScale.compareTo(new BigDecimal(MAX_SCALE)) > 0)
            return;

        OrderedPair mousePosOnScreen = new OrderedPair(location.x(), location.y());

        int width = getWidth();
        int height = getHeight();

		this.scrollX = this.scrollX.multiply(modifier).subtract(new BigDecimal(mousePosOnScreen.x().doubleValue() / width * (width * modifier.doubleValue() - width)));
		this.scrollY = this.scrollY.multiply(modifier).subtract(new BigDecimal(mousePosOnScreen.y().doubleValue() / height * (height * modifier.doubleValue() - height)));

        this.cellScale = tentativeNewScale.doubleValue();
    }

    private void drawGridlinesIfApplicable(Graphics g) {
        if (GridDisplay.this.cellScale < Preferences.getInstance().cellScaleToStopDrawingGridlines.get())
            return;

		Preferences.getInstance().gridlineType.get().draw(g, this);
    }

    private void drawAllAgents(Graphics g) {
		for (OrderedPair location : getCellLocationsOnScreen()) {
			SimulationContext.getAutomaton().findAgentsAt(location)
					.forEach(agent -> {
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
        State state = SimulationContext.getAutomaton().getCellAt(location);
        if (isDefaultState(state)) return;
        g.setColor(state.getColorEvaluator().getColor(DisplayContext.getColorPalette()));
        OrderedPair locationInScreenSpace = fromCellSpaceToScreenSpace(location);
        int cellSize = (int) this.cellScale + 1; // 1 is added to prevent gaps
        g.fillRect(locationInScreenSpace.x().intValue(), locationInScreenSpace.y().intValue(), cellSize, cellSize);
        state.customDraw(g.create(locationInScreenSpace.x().intValue(), locationInScreenSpace.y().intValue(), cellSize, cellSize));

		if (Preferences.getInstance().debug.get().drawUpdater.get() && SimulationContext.getAutomaton().isToBeUpdated(location)) {
			g.setColor(Color.YELLOW);
			g.fillRect(locationInScreenSpace.x().intValue(), locationInScreenSpace.y().intValue(), cellSize / 2, cellSize / 2);
		}
    }

    public OrderedPair fromCellSpaceToScreenSpace(OrderedPair cellSpace) {
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
        return SimulationContext.getAutomaton().getDefaultState().equals(state);
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
        g.setColor(SimulationContext.getAutomaton().getDefaultState().getColorEvaluator().getColor(DisplayContext.getColorPalette()));
        g.fillRect(0, 0, screenSizeInPixels.x().intValue(), screenSizeInPixels.y().intValue());
    }

    public OrderedPair getScreenSizeInPixels() {
        Dimension dimension = getSize();
        return new OrderedPair(dimension.width, dimension.height);
    }
}

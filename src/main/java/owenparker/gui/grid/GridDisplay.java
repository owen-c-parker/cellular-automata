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
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collection;
import java.util.LinkedList;

public class GridDisplay extends AccessorComponent<JPanel> {
    private static final double ZOOM_AMOUNT = .1;
    private static final double MIN_SCALE = 1;
    private static final double MAX_SCALE = 300;
    private static final double DEFAULT_CELL_SCALE = 20;

	private Bounds boundsToRender = null;

    private double cellScale;
    public double scrollX;
    public double scrollY;

    public double getCellScale() {
        return this.cellScale;
    }

	public void resetCamera() {
		this.cellScale = DEFAULT_CELL_SCALE;
		this.scrollX = 0;
		this.scrollY = 0;
	}

	public void renderBounds(Bounds bounds) {
		this.boundsToRender = bounds;
		this.globalReference.repaintEvent.fire();
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

		int top    = Math.min(bounds.a().y(), bounds.b().y());
		int bottom = Math.max(bounds.a().y(), bounds.b().y());
		int left   = Math.min(bounds.a().x(), bounds.b().x());
		int right  = Math.max(bounds.a().x(), bounds.b().x());

		g2d.drawRect(
				left,
				top,
				right - left + (int) this.cellScale,
				bottom - top + (int) this.cellScale
		);
	}

	private void drawBordersIfApplicable(Graphics g) {
        if (!globalReference.getAutomaton().getConfig().borderPolicy.get().equals(BorderPolicy.FILL_WITH_DEFAULT))
            return;

        OrderedPair topLeft = fromCellSpaceToScreenSpace(new OrderedPair(0, 0));
        OrderedPair bottomRight = fromCellSpaceToScreenSpace(globalReference.getAutomaton().getConfig().getSize());

        OrderedPair screenSize = new OrderedPair(getComponent().getSize());

        g.setColor(globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));

        g.fillRect(0, 0, screenSize.x(), topLeft.y()); // Top
        g.fillRect(0, bottomRight.y(), screenSize.x(), screenSize.y() - bottomRight.y()); // Bottom
        g.fillRect(0, 0, topLeft.x(), screenSize.y()); // Left
        g.fillRect(bottomRight.x(), 0, screenSize.x() - bottomRight.x(), screenSize.y()); // Right
    }

    public GridDisplay(GlobalReference globalReference) {
        super(globalReference);
        setComponent(new GridDisplayPanel());
        globalReference.gridDisplay = this;
		globalReference.cursorModeChangeEvent.add(() -> this.boundsToRender = null);
    }

    public void performZoom(MouseWheelEvent event) {
        double modifier = 1 + -ZOOM_AMOUNT * event.getPreciseWheelRotation();

		OrderedPair location = new OrderedPair(event);

        double tentativeNewScale = this.cellScale * modifier;

        if (tentativeNewScale < MIN_SCALE || tentativeNewScale > MAX_SCALE)
            return;

        OrderedPair mousePosOnScreen = new OrderedPair(location.x(), location.y());

        int width = getComponent().getWidth();
        int height = getComponent().getHeight();

        this.scrollX = this.scrollX * modifier - ((double) mousePosOnScreen.x() / width * (width * modifier - width));
        this.scrollY = this.scrollY * modifier - ((double) mousePosOnScreen.y() / height * (height * modifier - height));

        this.cellScale = tentativeNewScale;
    }

    private void drawGridlinesIfApplicable(Graphics g) {
        if (GridDisplay.this.cellScale < this.globalReference.preferences.cellScaleToStopDrawingGridlines.get())
            return;

        int sizeOfLines = (int) Math.clamp(4 + (30 / this.cellScale), 0, 3);

        OrderedPair screenSizeInPixels = getScreenSizeInPixels();
        g.setColor(this.globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));
        for (double x = this.scrollX % this.cellScale; x < screenSizeInPixels.x(); x += this.cellScale)
            g.fillRect((int) x - 1, 0, sizeOfLines, screenSizeInPixels.y());
        for (double y = this.scrollY % this.cellScale; y < screenSizeInPixels.y(); y += this.cellScale)
            g.fillRect(0, (int) y - 1, screenSizeInPixels.x(), sizeOfLines);
    }

    private void drawAllAgents(Graphics g) {
		for (OrderedPair location : getCellLocationsOnScreen()) {
			this.globalReference.getAutomaton().findAgentsAt(location)
					.forEach(agent -> agent.draw(g.create((int) (location.x() * this.cellScale + this.scrollX), (int) (location.y() * this.cellScale + this.scrollY), (int) this.cellScale, (int) this.cellScale)));
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
        g.fillRect(locationInScreenSpace.x(), locationInScreenSpace.y(), cellSize, cellSize);
        state.customDraw(g.create(locationInScreenSpace.x(), locationInScreenSpace.y(), cellSize, cellSize));

		if (this.globalReference.preferences.debug.get().drawUpdater.get() && this.globalReference.getAutomaton().isToBeUpdated(location)) {
			g.setColor(Color.YELLOW);
			g.fillRect(locationInScreenSpace.x(), locationInScreenSpace.y(), cellSize / 2, cellSize / 2);
		}
    }

    private OrderedPair fromCellSpaceToScreenSpace(OrderedPair cellSpace) {
        return cellSpace
                .scale(this.cellScale)
                .add((int) Math.floor(this.scrollX), (int) Math.floor(this.scrollY));
    }

	private Bounds fromCellSpaceToScreenSpace(Bounds bounds) {
		return new Bounds(fromCellSpaceToScreenSpace(bounds.a()), fromCellSpaceToScreenSpace(bounds.b()));
	}

    public OrderedPair fromScreenSpaceToCellSpace(OrderedPair screenSpace) {
		return screenSpace
                .subtract((int) Math.floor(this.scrollX), (int) Math.floor(this.scrollY))
				.divide(this.cellScale);
    }

	public Bounds fromScreenSpaceToCellSpace(Bounds bounds) {
		return new Bounds(fromScreenSpaceToCellSpace(bounds.a()), fromScreenSpaceToCellSpace(bounds.b()));
	}

    private boolean isDefaultState(State state) {
        return this.globalReference.getAutomaton().getDefaultState().equals(state);
    }

    public Collection<OrderedPair> getCellLocationsOnScreen() {
        OrderedPair scrollAsCells = new OrderedPair((int) -this.scrollX, (int) -this.scrollY).divide(this.cellScale);
        OrderedPair screenSizeInCells = getScreenSizeInPixels().divide(this.cellScale);

        Collection<OrderedPair> locations = new LinkedList<>();
        for (int y = scrollAsCells.y() - 1; y <= scrollAsCells.y() + screenSizeInCells.y() + 1; y++) {
            for (int x = scrollAsCells.x() - 1; x <= scrollAsCells.x() + screenSizeInCells.x() + 1; x++) {
                locations.add(new OrderedPair(x, y));
            }
        }

        return locations;
    }

    private void fillBackgroundWithDefaultCellColor(Graphics g) {
        OrderedPair screenSizeInPixels = getScreenSizeInPixels();
        g.setColor(this.globalReference.getAutomaton().getDefaultState().getColorEvaluator().getColor(this.globalReference.getColorPalette()));
        g.fillRect(0, 0, screenSizeInPixels.x(), screenSizeInPixels.y());
    }

    private OrderedPair getScreenSizeInPixels() {
        Dimension dimension = getComponent().getSize();
        return new OrderedPair(dimension.width, dimension.height);
    }
}

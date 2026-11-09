package owenparker.gui.grid;

import owenparker.engine.AgentFactory;
import owenparker.engine.BorderPolicy;
import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.Collection;
import java.util.LinkedList;

public class GridDisplay extends AccessorComponent<JPanel> {
    private static final double ZOOM_AMOUNT = .1;
    private static final double MIN_SCALE = 1;
    private static final double MAX_SCALE = 300;
    private static final double DEFAULT_CELL_SCALE = 20;

    private double cellScale;
    private double scrollX;
    private double scrollY;

    private MouseEvent lastMouseEvent = null;
    private MouseEvent shiftClickStartPlace = null;
    private MouseEvent mostRecentMove = null;

    public double getCellScale() {
        return this.cellScale;
    }

	public void resetCamera() {
		this.cellScale = DEFAULT_CELL_SCALE;
		this.scrollX = 0;
		this.scrollY = 0;
	}

	private class GridDisplayPanel extends JPanel {
        public GridDisplayPanel() {
			resetCamera();

			setBorder(new LineBorder(GridDisplay.this.globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES), 1, false));

            GridDisplay.this.globalReference.repaintEvent.add(() -> {
                revalidate();
                repaint();
            });

            addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    GridDisplay.this.lastMouseEvent = null;
                    GridDisplay.this.shiftClickStartPlace = null;
                    GridDisplay.this.globalReference.repaintEvent.fire();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.isShiftDown())
                        GridDisplay.this.shiftClickStartPlace = e;
                    placeCellAtClickedLocation(e);
                    placeAgentAtClickedLocation(e);
                    GridDisplay.this.globalReference.repaintEvent.fire();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                    GridDisplay.this.mostRecentMove = e;
                    GridDisplay.this.globalReference.repaintEvent.fire();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                    placeCellAtClickedLocation(e);
                    dragScroll(e);
                    GridDisplay.this.lastMouseEvent = e;
                    GridDisplay.this.globalReference.repaintEvent.fire();
                }
            });

            addMouseWheelListener(new MouseInputAdapter() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    super.mouseWheelMoved(e);
                    performZoom(e);
                    GridDisplay.this.globalReference.repaintEvent.fire();
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
        }
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
    }

    private void performZoom(MouseWheelEvent event) {
        double modifier = 1 + -ZOOM_AMOUNT * event.getPreciseWheelRotation();

        double tentativeNewScale = this.cellScale * modifier;

        if (tentativeNewScale < MIN_SCALE || tentativeNewScale > MAX_SCALE)
            return;

        OrderedPair mousePosOnScreen = new OrderedPair(this.mostRecentMove.getX(), this.mostRecentMove.getY());

        int width = getComponent().getWidth();
        int height = getComponent().getHeight();

        this.scrollX = this.scrollX * modifier - ((double) mousePosOnScreen.x() / width * (width * modifier - width));
        this.scrollY = this.scrollY * modifier - ((double) mousePosOnScreen.y() / height * (height * modifier - height));

        this.cellScale = tentativeNewScale;
    }

    private void dragScroll(MouseEvent event) {
        if (!SwingUtilities.isMiddleMouseButton(event)) return;

        if (this.lastMouseEvent == null) return;

        OrderedPair offset = new OrderedPair(event.getX(), event.getY())
                .subtract(this.lastMouseEvent.getX(), this.lastMouseEvent.getY());

        this.scrollX += offset.x();
        this.scrollY += offset.y();
    }

    private void placeAgentAtClickedLocation(MouseEvent event) {
        if (!(this.globalReference.selectedForPlacement instanceof AgentFactory agentFactory))
            return;

        if (SwingUtilities.isLeftMouseButton(event)) {
            OrderedPair cellSpacePosition = fromScreenSpaceToCellSpace(new OrderedPair(event.getX(), event.getY()));

            this.globalReference.getAutomaton().addAgent(agentFactory.buildAgent(cellSpacePosition));
        }

        if (SwingUtilities.isRightMouseButton(event)) {
			this.globalReference.getAutomaton().deleteAgentAt(fromScreenSpaceToCellSpace(new OrderedPair(event.getX(), event.getY())));
        }

    }

    private void placeCellAtClickedLocation(MouseEvent event) {
        if (!(this.globalReference.selectedForPlacement instanceof State state))
            return;


        OrderedPair location = new OrderedPair(event.getX(), event.getY());
        OrderedPair cellLocation = fromScreenSpaceToCellSpace(location);

        if (this.shiftClickStartPlace != null) {
            OrderedPair lastClickedCellLocation = fromScreenSpaceToCellSpace(new OrderedPair(this.shiftClickStartPlace.getX(), this.shiftClickStartPlace.getY()));

            OrderedPair difference = lastClickedCellLocation.subtract(cellLocation);

            if (Math.abs(difference.x()) > Math.abs(difference.y())) {
                cellLocation = new OrderedPair(cellLocation.x(), lastClickedCellLocation.y());
            } else {
                cellLocation = new OrderedPair(lastClickedCellLocation.x(), cellLocation.y());
            }
        }


        State stateToPaint = null;
        if (SwingUtilities.isLeftMouseButton(event))
            stateToPaint = state;
        if (SwingUtilities.isRightMouseButton(event))
            stateToPaint = this.globalReference.getAutomaton().getDefaultState();
        if (stateToPaint == null) return;

        this.globalReference.getAutomaton().setCellAt(cellLocation, stateToPaint);
    }

    private void drawGridlinesIfApplicable(Graphics g) {
        if (GridDisplay.this.cellScale < globalReference.preferences.cellScaleToStopDrawingGridlines.get())
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
    }

    private OrderedPair fromCellSpaceToScreenSpace(OrderedPair cellSpace) {
        return cellSpace
                .scale(this.cellScale)
                .add((int) Math.floor(this.scrollX), (int) Math.floor(this.scrollY));
    }

    public OrderedPair fromScreenSpaceToCellSpace(OrderedPair screenSpace) {
        return screenSpace
                .subtract((int) Math.floor(this.scrollX), (int) Math.floor(this.scrollY))
                .divide(this.cellScale);
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

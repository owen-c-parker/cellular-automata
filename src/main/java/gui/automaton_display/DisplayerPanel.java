package gui.automaton_display;

import engine.Agent;
import engine.AgentBehavior;
import engine.OrderedPair;
import engine.State;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.Collection;
import java.util.LinkedList;

public class DisplayerPanel extends JPanel {
	private static final double ZOOM_AMOUNT = .1;
	private static final double MIN_SCALE = 1;
	private static final double SCALE_TO_STOP_DRAWING_GRIDLINES = 20;
	private static final double MAX_SCALE = 100;
	private static final double DEFAULT_CELL_SCALE = 20;
	private static final Color BORDER_COLOR = Color.BLACK;

	private final AutomatonDisplayer automatonDisplayer;
	private double cellScale = DEFAULT_CELL_SCALE;
	private double scrollX = 0;
	private double scrollY = 0;

	private MouseEvent lastMouseEvent = null;
	private MouseEvent mostRecentMove = null;
	private MouseEvent lastClickedPlace = null;

	public DisplayerPanel(AutomatonDisplayer automatonDisplayer) {
		this.automatonDisplayer = automatonDisplayer;

		// Click
		this.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				DisplayerPanel.this.lastMouseEvent = null;
				DisplayerPanel.this.lastClickedPlace = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseClicked(e);
				if (e.isShiftDown())
					DisplayerPanel.this.lastClickedPlace = e;
				placeCellAtClickedLocation(e);
                placeAgentAtClickedLocation(e);
			}
		});

		// Dragging
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				DisplayerPanel.this.mostRecentMove = e;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				placeCellAtClickedLocation(e);
				dragScroll(e);
				DisplayerPanel.this.lastMouseEvent = e;
			}
		});

		// Zooming
		this.addMouseWheelListener(new MouseInputAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				super.mouseWheelMoved(e);
				double modifier = 1 + -ZOOM_AMOUNT * e.getPreciseWheelRotation();

				double tentativeNewScale = DisplayerPanel.this.cellScale * modifier;

				if (tentativeNewScale < MIN_SCALE || tentativeNewScale > MAX_SCALE)
					return;

				OrderedPair mousePosOnScreen = new OrderedPair(DisplayerPanel.this.mostRecentMove.getX(), DisplayerPanel.this.mostRecentMove.getY());

				int width = automatonDisplayer.getWidth();
				int height = automatonDisplayer.getHeight();

				DisplayerPanel.this.scrollX = DisplayerPanel.this.scrollX * modifier - ((double) mousePosOnScreen.x() / width * (width * modifier - width));
				DisplayerPanel.this.scrollY = DisplayerPanel.this.scrollY * modifier - ((double) mousePosOnScreen.y() / height * (height * modifier - height));

				DisplayerPanel.this.cellScale = tentativeNewScale;

				repaint();
			}
		});


	}

    private void placeAgentAtClickedLocation(MouseEvent e) {
        if (!(this.automatonDisplayer.selectedForPlacing instanceof AgentBehavior agentBehavior))
            return;

        OrderedPair cellSpacePosition = fromScreenSpaceToCellSpace(new OrderedPair(e.getX(), e.getY()));

        this.automatonDisplayer.automaton.addAgent(new Agent(cellSpacePosition, agentBehavior));
    }

    public OrderedPair fromScreenSpaceToCellSpace(OrderedPair screenSpace) {
		return screenSpace
				.subtract((int) this.scrollX, (int) this.scrollY)
				.divide(this.cellScale);
	}

	public OrderedPair fromCellSpaceToScreenSpace(OrderedPair cellSpace) {
		return cellSpace
				.scale(this.cellScale)
				.add((int) this.scrollX, (int) this.scrollY);
	}

	public void placeCellAtClickedLocation(MouseEvent event) {
        if (!(this.automatonDisplayer.selectedForPlacing instanceof State state))
            return;


        OrderedPair location = new OrderedPair(event.getX(), event.getY());
		OrderedPair cellLocation = fromScreenSpaceToCellSpace(location);

		if (this.lastClickedPlace != null) {
			OrderedPair lastClickedCellLocation = fromScreenSpaceToCellSpace(new OrderedPair(this.lastClickedPlace.getX(), this.lastClickedPlace.getY()));

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
			stateToPaint = this.automatonDisplayer.automaton.getDefaultState();
		if (stateToPaint == null) return;

		this.automatonDisplayer.automaton.setCellAt(cellLocation, stateToPaint);
	}

	private void dragScroll(MouseEvent event) {
		if (!SwingUtilities.isMiddleMouseButton(event)) return;

		if (this.lastMouseEvent == null) return;

		OrderedPair offset = new OrderedPair(event.getX(), event.getY())
				.subtract(this.lastMouseEvent.getX(), this.lastMouseEvent.getY());

		this.scrollX += offset.x();
		this.scrollY += offset.y();

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		fillBackgroundWithDefaultCellColor(g);
		drawAllCells(g);
        drawAllAgents(g);
		if (this.cellScale >= SCALE_TO_STOP_DRAWING_GRIDLINES)
			drawGridlines(g);
		this.automatonDisplayer.drawCallbacks.forEach(Runnable::run);
	}

    private void drawAllAgents(Graphics g) {
        Collection<Agent> agents = this.automatonDisplayer.automaton.getAgents();
        for (Agent agent : agents)
            agent.draw(g.create((int) (agent.getPosition().x() * this.cellScale + this.scrollX), (int) (agent.getPosition().y() * this.cellScale + this.scrollY), (int) this.cellScale, (int) this.cellScale));
    }

    private void drawGridlines(Graphics g) {
		OrderedPair screenSizeInPixels = getScreenSizeInPixels();
		g.setColor(BORDER_COLOR);
		for (double x = this.scrollX % this.cellScale; x < screenSizeInPixels.x(); x += this.cellScale)
			g.fillRect((int) x - 1, 0, 3, screenSizeInPixels.y());
		for (double y = this.scrollY % this.cellScale; y < screenSizeInPixels.y(); y += this.cellScale)
			g.fillRect(0, (int) y - 1, screenSizeInPixels.x(), 3);
	}

	private void drawAllCells(Graphics g) {
		for (OrderedPair location : getCellLocationsOnScreen())
			drawCellAt(g, location);
	}

	private OrderedPair getScreenSizeInPixels() {
		Dimension dimension = getSize();
		return new OrderedPair(dimension.width, dimension.height);
	}

	private void fillBackgroundWithDefaultCellColor(Graphics g) {
		OrderedPair screenSizeInPixels = getScreenSizeInPixels();
		g.setColor(this.automatonDisplayer.automaton.getDefaultState().getColor());
		g.fillRect(0, 0, screenSizeInPixels.x(), screenSizeInPixels.y());
	}

	private void drawCellAt(Graphics g, OrderedPair location) {
		State state = this.automatonDisplayer.automaton.getCellAt(location);
		if (isDefaultState(state)) return;
		g.setColor(state.getColor());
		OrderedPair locationInScreenSpace = fromCellSpaceToScreenSpace(location);
		int cellSize = (int) this.cellScale + 1; // 1 is added to prevent gaps
		g.fillRect(locationInScreenSpace.x(), locationInScreenSpace.y(), cellSize, cellSize);
		state.customDraw(g.create(locationInScreenSpace.x(), locationInScreenSpace.y(), cellSize, cellSize));
	}

	private boolean isDefaultState(State state) {
		return state.equals(this.automatonDisplayer.automaton.getDefaultState());
	}

	public double getCellScale() {
		return this.cellScale;
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
}

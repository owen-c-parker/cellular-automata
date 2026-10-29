package gui;

import engine.Automaton;
import engine.OrderedPair;
import engine.State;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AutomatonDisplayer extends JFrame {
	private static final double ZOOM_AMOUNT = .1;
	private static final double MIN_SCALE = 5;
	private static final double MAX_SCALE = 100;

	private final Automaton automaton;

	private State currentPaintedState;

	private class DisplayerPanel extends JPanel {
		private double cellScale;
		private double scrollX = 0;
		private double scrollY = 0;

		private MouseEvent lastMouseEvent = null;
		private MouseEvent mostRecentMove = null;
		private MouseEvent lastClickedPlace = null;

		public OrderedPair fromPixelSpaceToCellSpace(OrderedPair pixelSpace) {
			return pixelSpace
					.subtract((int) this.scrollX, (int) this.scrollY)
					.divide(this.cellScale);
		}

		public OrderedPair fromCellSpaceToPixelSpace(OrderedPair cellSpace) {
			return cellSpace
					.scale(this.cellScale)
					.add((int) this.scrollX, (int) this.scrollY);
		}

		public void placeCellAtClickedLocation(MouseEvent event) {
			OrderedPair location = new OrderedPair(event.getX(), event.getY());
			OrderedPair cellLocation = fromPixelSpaceToCellSpace(location);

			if (this.lastClickedPlace != null) {
				OrderedPair lastClickedCellLocation = fromPixelSpaceToCellSpace(new OrderedPair(this.lastClickedPlace.getX(), this.lastClickedPlace.getY()));

				OrderedPair difference = lastClickedCellLocation.subtract(cellLocation);

				if (Math.abs(difference.x()) > Math.abs(difference.y())) {
					cellLocation = new OrderedPair(cellLocation.x(), lastClickedCellLocation.y());
				} else {
					cellLocation = new OrderedPair(lastClickedCellLocation.x(), cellLocation.y());
				}
			}


			State stateToPaint = null;
			if (SwingUtilities.isLeftMouseButton(event))
				stateToPaint = AutomatonDisplayer.this.currentPaintedState;
			if (SwingUtilities.isRightMouseButton(event))
				stateToPaint = AutomatonDisplayer.this.automaton.getDefaultState();
			if (stateToPaint == null) return;

			AutomatonDisplayer.this.automaton.setCellAt(cellLocation, stateToPaint);
		}

		public DisplayerPanel(double cellScale) {
			this.cellScale = cellScale;

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

					if (tentativeNewScale < MIN_SCALE || tentativeNewScale > MAX_SCALE) return;

					OrderedPair mousePosOnScreen = new OrderedPair(DisplayerPanel.this.mostRecentMove.getX(), DisplayerPanel.this.mostRecentMove.getY());

					int width = AutomatonDisplayer.this.getWidth();
					int height = AutomatonDisplayer.this.getHeight();

					DisplayerPanel.this.scrollX = DisplayerPanel.this.scrollX * modifier - ((double) mousePosOnScreen.x() / width * (width * modifier - width));
					DisplayerPanel.this.scrollY = DisplayerPanel.this.scrollY * modifier - ((double) mousePosOnScreen.y() / height * (height * modifier - height));

					DisplayerPanel.this.cellScale = tentativeNewScale;

					repaint();
				}
			});


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


			Dimension dimension = getSize();

			OrderedPair screenSizeInPixels = new OrderedPair(dimension.width, dimension.height);

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, screenSizeInPixels.x(), screenSizeInPixels.y());

			OrderedPair screenSizeInCells = screenSizeInPixels.divide(this.cellScale);

			OrderedPair scrollAsCells = new OrderedPair((int) -this.scrollX, (int) -this.scrollY).divide(this.cellScale);

			for (int y = scrollAsCells.y() - 1; y <= scrollAsCells.y() + screenSizeInCells.y() + 1; y++) {
				for (int x = scrollAsCells.x() - 1; x <= scrollAsCells.x() + screenSizeInCells.x() + 1; x++) {
					State cell = AutomatonDisplayer.this.automaton.getCellAt(new OrderedPair(x, y));
					g.setColor(cell.getColor());
					OrderedPair pixelLocation = fromCellSpaceToPixelSpace(new OrderedPair(x, y));
					g.fillRect(pixelLocation.x(), pixelLocation.y(), (int) (this.cellScale * .95), (int) (this.cellScale * .95));
				}
			}
		}
	}

	private class DisplayerMenuBar extends JMenuBar {
		public DisplayerMenuBar() {
			JMenu paintSelector = new JMenu("Paint");

			ButtonGroup paintSelectorButtonGroup = new ButtonGroup();
			int index = 0;
			for (State state : AutomatonDisplayer.this.automaton.getSelectableForPainting()) {
				JRadioButtonMenuItem button = new JRadioButtonMenuItem(state.getDisplayableName());
				button.setForeground(getBrightness(state.getColor()) > .5 ? Color.BLACK : Color.WHITE);
				button.setBackground(state.getColor());
				button.addActionListener(_ -> AutomatonDisplayer.this.currentPaintedState = state);
				paintSelectorButtonGroup.add(button);
				if (index == 1)
					paintSelectorButtonGroup.setSelected(button.getModel(), true);
				paintSelector.add(button);
				index++;
			}

			this.add(paintSelector);
		}

		private double getBrightness(Color color) {
			return ((color.getRed() + color.getGreen() + color.getBlue()) / 3.0) / 256.0;
		}
	}

	public AutomatonDisplayer(Automaton automaton, int cellScale) {
		this.automaton = automaton;

		List<State> selectable = automaton.getSelectableForPainting();

		this.currentPaintedState = selectable.size() > 1 ? selectable.get(1) : selectable.getFirst();

		this.setJMenuBar(new DisplayerMenuBar());
		this.setContentPane(new DisplayerPanel(cellScale));

		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		repaint();
		automaton.registerUpdateListener(this::repaint);
	}
}

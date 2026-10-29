package gui.automatonDisplay;

import engine.Automaton;
import engine.State;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class AutomatonDisplayer extends JFrame {
	final Automaton automaton;

	State currentPaintedState;

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
		this.setContentPane(new DisplayerPanel(this, cellScale));

		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		repaint();
		automaton.registerUpdateListener(this::repaint);
	}
}

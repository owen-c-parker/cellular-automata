package gui.automaton_display.menu_options.tool_options;

import engine.Automaton;
import engine.OrderedPair;
import engine.State;
import gui.automaton_display.AutomatonDisplayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class ToolFillWithNoise extends JMenu {

	private final Automaton automaton;
	private final AutomatonDisplayer displayer;

	private class ShufflingListener implements ActionListener {

		private final Function<Random, State> stateChooser;

		public ShufflingListener(Function<Random, State> stateChooser) {
			this.stateChooser = stateChooser;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Random random = new Random();
			for (OrderedPair location : ToolFillWithNoise.this.displayer.getDisplayerPanel().getCellLocationsOnScreen()) {
				ToolFillWithNoise.this.automaton.setCellAt(location, this.stateChooser.apply(random));
			}
		}
	}

	private class JustCurrentState extends JMenuItem {
		public JustCurrentState() {
			super("Only selected cell state");

			addActionListener(new ShufflingListener(r -> r.nextBoolean() ? ToolFillWithNoise.this.automaton.getDefaultState() : ToolFillWithNoise.this.displayer.currentPaintedState));
		}
	}

	private class AllStates extends JMenuItem {
		public AllStates() {
			super("Use all available cell states");

			addActionListener(new ShufflingListener(r -> {
				List<State> selectable = ToolFillWithNoise.this.automaton.getSelectableForPainting();
				return selectable.get(r.nextInt(selectable.size()));
			}));
		}
	}

	public ToolFillWithNoise(Automaton automaton, AutomatonDisplayer displayer) {
		this.automaton = automaton;
		this.displayer = displayer;
		super("Noise");
		add(new JustCurrentState());
		add(new AllStates());
	}
}

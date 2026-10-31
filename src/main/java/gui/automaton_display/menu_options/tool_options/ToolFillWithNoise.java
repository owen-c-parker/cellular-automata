package gui.automaton_display.menu_options.tool_options;

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
				ToolFillWithNoise.this.displayer.automaton.setCellAt(location, this.stateChooser.apply(random));
			}
		}
	}

	private class AllStates extends JMenuItem {
		public AllStates() {
			super("Use all available cell states");

			addActionListener(new ShufflingListener(r -> {
				List<State> selectable = ToolFillWithNoise.this.displayer.automaton.getSelectableCellStates();
				return selectable.get(r.nextInt(selectable.size()));
			}));
		}
	}

	public ToolFillWithNoise(AutomatonDisplayer displayer) {
		this.displayer = displayer;
		super("Noise");
		add(new AllStates());
	}
}

package owenparker.gui.control;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigInteger;
import owenparker.engine.Automaton;
import owenparker.gui.ConfirmationWindow;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;

public class WarpButton extends JButton {
	public WarpButton() {
		super("⏭");
		setToolTipText("Jump forward a number of steps");

		addActionListener(_ -> this.click());
	}

	private static class WarpConfig extends Configuration {
		public ConfigInteger stepCount = new ConfigInteger("Number of steps to jump forward", 1_000, 0, Integer.MAX_VALUE);
	}

	private void click() {
		new ConfirmationWindow<>(new WarpConfig(), c -> {
			int stepCount = c.stepCount.get();

			Automaton automaton = SimulationContext.getAutomaton();
			for (; stepCount > 0; stepCount--) {
				automaton.step();
			}

		}, "Warp");
	}
}

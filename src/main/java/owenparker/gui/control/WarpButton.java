package owenparker.gui.control;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigInteger;
import owenparker.engine.Automaton;
import owenparker.gui.AccessorComponent;
import owenparker.gui.ConfirmationWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class WarpButton extends AccessorComponent<JButton> {
	public WarpButton(GlobalReference globalReference) {
		super(globalReference, new JButton("⏭"));
		getComponent().setToolTipText("Jump forward a number of steps");

		getComponent().addActionListener(_ -> this.click());
	}

	private static class WarpConfig extends Configuration {
		public ConfigInteger stepCount = new ConfigInteger("Number of steps to jump forward", 1_000, 0, Integer.MAX_VALUE);
	}

	private void click() {
		new ConfirmationWindow<>(new WarpConfig(), c -> {
			int stepCount = c.stepCount.get();

			Automaton automaton = this.globalReference.getAutomaton();
			for (; stepCount > 0; stepCount--) {
				automaton.step();
			}

		}, "Warp");
	}
}

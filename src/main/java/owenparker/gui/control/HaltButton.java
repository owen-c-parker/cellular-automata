package owenparker.gui.control;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GenericWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HaltButton extends AccessorComponent<JButton> {
	public HaltButton(GlobalReference globalReference) {
		super(globalReference, new JButton("⏭"));
		getComponent().setToolTipText("Simulate until halt state is found");

		getComponent().addActionListener(_ -> this.click());
	}

	private void click() {
		int MAX_ITERATIONS = this.globalReference.preferences.maxIterationsForHalting.get();

		// This is gonna use a LOT of memory...
		// I tried only storing hashes, but collisions were far too common
		Set<Map<OrderedPair, State>> boardMaps = new HashSet<>();

		int steps = 0;
		boolean halted = false;
		for (; steps < MAX_ITERATIONS; steps++) {
			this.globalReference.getAutomaton().step();
			Map<OrderedPair, State> boardMap = this.globalReference.getAutomaton().getBoardMap();
			if (boardMaps.contains(boardMap)) {
				halted = true;
				break;
			}
			boardMaps.add(boardMap);
		}

		GenericWindow window = new GenericWindow();

		if (halted)
			window.getContentPane().add(new JLabel(String.format("Halted after %d steps!", steps)));
		else
			window.getContentPane().add(new JLabel(String.format("Didn't halt after %d steps", steps)));

		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}

package owenparker.stress_testing;

import owenparker.engine.Automaton;
import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.plugins.default_content.automaton_factories.CGOLFactory;

import java.awt.desktop.SystemEventListener;

public class StressTest {
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	private static final int STEPS = 1000;

	public static void main(String[] args) {
		Automaton automaton = new CGOLFactory().buildAutomaton();
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (Math.random() < .5)
					automaton.setCellAt(new OrderedPair(x, y), (State) automaton.getSelectedByDefault());
			}
		}

		int step;
		for (step = 0; step < STEPS; step++) {
			automaton.step();
			if (automaton.getActiveCellCount() == 0)
				break;
		}

		System.out.println(step + " steps taken");
	}
}

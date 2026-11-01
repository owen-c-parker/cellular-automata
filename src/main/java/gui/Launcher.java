package gui;

import automata.*;
import engine.Automaton;

import java.util.Collection;
import java.util.List;

public class Launcher {

	public static Collection<Class<? extends Automaton>> getAvailableAutomatonClasses() {
		return List.of(
				CGOL.class,
				KnightCGOL.class,
				Rule110.class,
				Wireworld.class,
				BriansBrain.class,
				LangtonsAnt.class
		);
	}

	void main() {
		launch();
	}

	private void launch() {
        new AutomatonWindow();
	}
}

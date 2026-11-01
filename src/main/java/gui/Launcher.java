package gui;

import automata.*;
import automaton_factories.CGOLFactory;
import engine.Automaton;
import engine.AutomatonFactory;

import java.util.Collection;
import java.util.List;

public class Launcher {

	public static Collection<AutomatonFactory> getAvailableAutomatonClasses() {
		return List.of(
				new CGOLFactory()
		);
	}

	void main() {
		launch();
	}

	private void launch() {
        new AutomatonWindow();
	}
}

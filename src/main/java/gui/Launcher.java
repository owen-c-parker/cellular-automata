package gui;

import automata.*;
import engine.Automaton;
import gui.automaton_display.AutomatonDisplayer;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.List;

public class Launcher {

	public static Collection<Class<? extends Automaton>> getAvailableAutomatonClasses() {
		return List.of(
				CGOL.class,
				KnightCGOL.class,
				Rule110.class,
				Wireworld.class,
				BriansBrain.class
		);
	}

	void main() {
		launch();
	}

	private void launch() {
		Automaton automaton = new CGOL();
		AutomatonDisplayer displayer = new AutomatonDisplayer();
		displayer.setAutomaton(automaton);
	}
}

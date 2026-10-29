package gui;

import automata.CGOL;
import automata.KnightCGOL;
import automata.Rule110;
import automata.Wireworld;
import engine.Automaton;
import gui.automaton_display.AutomatonDisplayer;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Launcher {

	void main() {
		launch();
	}

	private void launch() {
		Automaton automaton = new CGOL();
		AutomatonDisplayer displayer = new AutomatonDisplayer(automaton, 16);
		new AutomatonController(automaton);
		new StatsDisplayer(automaton, displayer);
	}
}

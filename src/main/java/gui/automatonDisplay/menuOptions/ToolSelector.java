package gui.automatonDisplay.menuOptions;

import engine.Automaton;
import gui.automatonDisplay.AutomatonDisplayer;
import gui.automatonDisplay.menuOptions.toolOptions.ToolClear;
import gui.automatonDisplay.menuOptions.toolOptions.ToolFillWithStatic;

import javax.swing.*;

public class ToolSelector extends JMenu {
	public ToolSelector(Automaton automaton, AutomatonDisplayer displayer) {
		super("Tools");
		add(new ToolFillWithStatic(automaton, displayer));
		add(new ToolClear(automaton));
	}
}

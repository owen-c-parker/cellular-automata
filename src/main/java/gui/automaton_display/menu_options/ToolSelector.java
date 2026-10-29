package gui.automaton_display.menu_options;

import engine.Automaton;
import gui.automaton_display.AutomatonDisplayer;
import gui.automaton_display.menu_options.tool_options.ToolClear;
import gui.automaton_display.menu_options.tool_options.ToolFillWithNoise;

import javax.swing.*;

public class ToolSelector extends JMenu {
	public ToolSelector(Automaton automaton, AutomatonDisplayer displayer) {
		super("Tools");
		add(new ToolFillWithNoise(automaton, displayer));
		add(new ToolClear(automaton));
	}
}

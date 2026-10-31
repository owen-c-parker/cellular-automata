package gui.OLD.automaton_display.menu_options;

import gui.OLD.automaton_display.AutomatonDisplayer;
import gui.OLD.automaton_display.menu_options.tool_options.ToolClear;
import gui.OLD.automaton_display.menu_options.tool_options.ToolFillWithNoise;

import javax.swing.*;

public class ToolSelector extends JMenu {
	public ToolSelector(AutomatonDisplayer displayer) {
		super("Tools");
		add(new ToolFillWithNoise(displayer));
		add(new ToolClear(displayer));
	}
}

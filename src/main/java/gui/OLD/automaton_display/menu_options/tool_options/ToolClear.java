package gui.OLD.automaton_display.menu_options.tool_options;

import gui.OLD.automaton_display.AutomatonDisplayer;

import javax.swing.*;

public class ToolClear extends JMenuItem {
	public ToolClear(AutomatonDisplayer displayer) {
		super("Clear all cells");
		addActionListener(_ -> {
			displayer.automaton.clear();
		});
	}
}

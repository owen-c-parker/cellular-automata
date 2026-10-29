package gui.automaton_display.menu_options.tool_options;

import engine.Automaton;

import javax.swing.*;

public class ToolClear extends JMenuItem {
	public ToolClear(Automaton automaton) {
		super("Clear all cells");
		addActionListener(_ -> {
			automaton.clear();
		});
	}
}

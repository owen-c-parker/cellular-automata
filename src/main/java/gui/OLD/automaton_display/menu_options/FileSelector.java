package gui.OLD.automaton_display.menu_options;

import gui.OLD.automaton_display.AutomatonDisplayer;
import gui.OLD.automaton_display.menu_options.file_options.FileNew;

import javax.swing.*;

public class FileSelector extends JMenu {
	public FileSelector(AutomatonDisplayer displayer) {
		super("File");
		add(new FileNew(displayer));
	}
}

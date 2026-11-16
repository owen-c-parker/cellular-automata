package owenparker.gui.menu_bar.file;

import owenparker.gui.FileManagement;
import owenparker.gui.context.DisplayContext;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutomatonFileChooser extends JFileChooser {
	public AutomatonFileChooser(String title) {
		super(FileManagement.getSavesDirectory());
		setDialogTitle(title);
		setFileFilter(new FileNameExtensionFilter("State of an automaton", "automaton"));
	}
}

package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.FileManagement;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutomatonFileChooser extends AccessorComponent<JFileChooser> {
	public AutomatonFileChooser(GlobalReference globalReference, String title) {
		super(globalReference, new JFileChooser(FileManagement.getSavesDirectory()));
		getComponent().setDialogTitle(title);
		getComponent().setFileFilter(new FileNameExtensionFilter("State of an automaton", "automaton"));
	}
}

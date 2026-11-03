package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.FileManagement;
import owenparker.gui.GuiReference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutomatonFileChooser extends AccessorComponent<JFileChooser> {
	public AutomatonFileChooser(GuiReference guiReference, String title) {
		super(guiReference, new JFileChooser(FileManagement.getSavesDirectory()));
		getComponent().setDialogTitle(title);
		getComponent().setFileFilter(new FileNameExtensionFilter("Automaton state", "automaton"));
	}
}

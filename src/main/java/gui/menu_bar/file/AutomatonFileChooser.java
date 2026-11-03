package gui.menu_bar.file;

import gui.AccessorComponent;
import gui.FileManagement;
import gui.GuiReference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.Path;

public class AutomatonFileChooser extends AccessorComponent<JFileChooser> {
	public AutomatonFileChooser(GuiReference guiReference, String title) {
		super(guiReference, new JFileChooser(FileManagement.getSavesDirectory()));
		getComponent().setDialogTitle(title);
		getComponent().setFileFilter(new FileNameExtensionFilter("Automaton state", "automaton"));
	}
}

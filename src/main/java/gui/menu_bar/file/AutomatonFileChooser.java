package gui.menu_bar.file;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutomatonFileChooser extends AccessorComponent<JFileChooser> {
	public AutomatonFileChooser(GuiReference guiReference, String title) {
		super(guiReference, new JFileChooser());
		getComponent().setDialogTitle(title);
		getComponent().setFileFilter(new FileNameExtensionFilter("Automaton state", "automaton"));
	}
}

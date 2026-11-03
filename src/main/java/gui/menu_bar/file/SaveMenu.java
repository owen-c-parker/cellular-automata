package gui.menu_bar.file;

import gui.AccessorComponent;
import gui.GuiReference;
import gui.Launcher;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveMenu extends AccessorComponent<JMenuItem> {

	public SaveMenu(GuiReference guiReference) {
		super(guiReference, new JMenuItem("Save state"));

		getComponent().addActionListener(_ -> {
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Save state of automaton");
				fileChooser.showSaveDialog(getComponent());
				File fileToWriteTo = fileChooser.getSelectedFile();
				new ObjectOutputStream(new FileOutputStream(fileToWriteTo)).writeObject(guiReference.getAutomaton());
			} catch (IOException e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

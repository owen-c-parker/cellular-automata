package gui.menu_bar.file;

import engine.Automaton;
import gui.AccessorComponent;
import gui.FileManagement;
import gui.GuiReference;
import gui.Launcher;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadMenu extends AccessorComponent<JMenuItem> {
	public LoadMenu(GuiReference guiReference) {
		super(guiReference, new JMenuItem("Load state"));

		getComponent().addActionListener(_ -> {
			AutomatonFileChooser fileChooser = new AutomatonFileChooser(guiReference, "Load automaton state");
			fileChooser.getComponent().showOpenDialog(getComponent());
			File fileToRead = fileChooser.getComponent().getSelectedFile();
			if (fileToRead == null) {
				Launcher.errorWindow(new RuntimeException("No file selected"));
				return;
			}
			try {
				Automaton automaton = (Automaton) new ObjectInputStream(new FileInputStream(fileToRead)).readObject();
				guiReference.setAutomaton(automaton);
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

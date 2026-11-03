package gui.menu_bar.file;

import engine.Automaton;
import gui.AccessorComponent;
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
			JFileChooser fileChooser = new JFileChooser("Load automaton state");
			fileChooser.showOpenDialog(getComponent());
			File fileToRead = fileChooser.getSelectedFile();
			try {
				Automaton automaton = (Automaton) new ObjectInputStream(new FileInputStream(fileToRead)).readObject();
				guiReference.setAutomaton(automaton);
			} catch (IOException | ClassNotFoundException e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

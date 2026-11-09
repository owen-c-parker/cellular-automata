package owenparker.gui.menu_bar.file;

import owenparker.engine.Automaton;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.Launcher;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadMenu extends AccessorComponent<JMenuItem> {
	public LoadMenu(GlobalReference globalReference) {
		super(globalReference, new JMenuItem("Load"));

		getComponent().addActionListener(_ -> {
			AutomatonFileChooser fileChooser = new AutomatonFileChooser(globalReference, "Load");
			fileChooser.getComponent().showOpenDialog(getComponent());
			File fileToRead = fileChooser.getComponent().getSelectedFile();
			if (fileToRead == null)
				return;
			try {
				Automaton automaton = (Automaton) new ObjectInputStream(new FileInputStream(fileToRead)).readObject();
				globalReference.setAutomaton(automaton);
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

package owenparker.gui.menu_bar.file;

import owenparker.engine.Automaton;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.Launcher;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadMenu extends JMenuItem {
	public LoadMenu() {
		super("Load");

		addActionListener(_ -> {
			AutomatonFileChooser fileChooser = new AutomatonFileChooser("Load");
			fileChooser.showOpenDialog(getComponent());
			File fileToRead = fileChooser.getSelectedFile();
			if (fileToRead == null)
				return;
			try {
				Automaton automaton = (Automaton) new ObjectInputStream(new FileInputStream(fileToRead)).readObject();
				SimulationContext.setAutomaton(automaton);
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

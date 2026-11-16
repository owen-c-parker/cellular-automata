package owenparker.gui.menu_bar.file;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.Launcher;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveMenu extends JMenuItem {

	public SaveMenu() {
		super("Save");

		addActionListener(_ -> {
			AutomatonFileChooser fileChooser = new AutomatonFileChooser("Save");
			fileChooser.setSelectedFile(new File(".automaton"));
			int result = fileChooser.showSaveDialog(getComponent());
			if (result != JFileChooser.APPROVE_OPTION)
				return;

			File fileToWriteTo = fileChooser.getSelectedFile();
			if (fileToWriteTo == null)
				return;
			try {
				new ObjectOutputStream(new FileOutputStream(fileToWriteTo)).writeObject(SimulationContext.getAutomaton());
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;
import owenparker.gui.Launcher;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveMenu extends AccessorComponent<JMenuItem> {

	public SaveMenu(GuiReference guiReference) {
		super(guiReference, new JMenuItem("Save state"));

		getComponent().addActionListener(_ -> {
			AutomatonFileChooser fileChooser = new AutomatonFileChooser(guiReference, "Save automaton state");
			fileChooser.getComponent().setSelectedFile(new File(".automaton"));
			int result = fileChooser.getComponent().showSaveDialog(getComponent());
			if (result != JFileChooser.APPROVE_OPTION)
				return;

			File fileToWriteTo = fileChooser.getComponent().getSelectedFile();
			if (fileToWriteTo == null) {
				Launcher.errorWindow(new RuntimeException("No file selected"));
				return;
			}
			try {
				new ObjectOutputStream(new FileOutputStream(fileToWriteTo)).writeObject(guiReference.getAutomaton());
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

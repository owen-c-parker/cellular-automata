package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.Launcher;
import owenparker.lang.Language;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveMenu extends AccessorComponent<JMenuItem> {

	public SaveMenu(GlobalReference globalReference) {
		super(globalReference, new JMenuItem(translation(Language.Key.SAVE_MENU_OPTION)));

		getComponent().addActionListener(_ -> {
			AutomatonFileChooser fileChooser = new AutomatonFileChooser(globalReference, translation(Language.Key.SAVE_MENU_OPTION));
			fileChooser.getComponent().setSelectedFile(new File(".automaton"));
			int result = fileChooser.getComponent().showSaveDialog(getComponent());
			if (result != JFileChooser.APPROVE_OPTION)
				return;

			File fileToWriteTo = fileChooser.getComponent().getSelectedFile();
			if (fileToWriteTo == null) {
				Launcher.errorWindow(new RuntimeException(translation(Language.Key.NO_FILE_SELECTED)));
				return;
			}
			try {
				new ObjectOutputStream(new FileOutputStream(fileToWriteTo)).writeObject(globalReference.getAutomaton());
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		});
	}
}

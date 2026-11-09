package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class FileMenu extends AccessorComponent<JMenu> {
    public FileMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu("File"));

        add(new NewFileMenu(globalReference));
        getComponent().add(new JSeparator());
		add(new SaveMenu(globalReference));
		add(new LoadMenu(globalReference));
        getComponent().add(new JSeparator());
        add(new PreferencesMenu(globalReference));
    }
}

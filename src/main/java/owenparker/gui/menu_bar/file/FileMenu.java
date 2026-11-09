package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.lang.Language;

import javax.swing.*;

public class FileMenu extends AccessorComponent<JMenu> {
    public FileMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.FILE_MENU_OPTION)));

        add(new NewFileMenu(globalReference));
        add(new ConfigureAutomatonMenu(globalReference));
		add(new SaveMenu(globalReference));
		add(new LoadMenu(globalReference));
        add(new PreferencesMenu(globalReference));
    }
}

package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.lang.Language;

import javax.swing.*;

public class FileMenu extends AccessorComponent<JMenu> {
    public FileMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.FILE_MENU_OPTION)));

        getComponent().add(new NewFileMenu(globalReference).getComponent());
		getComponent().add(new SaveMenu(globalReference).getComponent());
		getComponent().add(new LoadMenu(globalReference).getComponent());
    }
}

package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

import javax.swing.*;

public class FileMenu extends AccessorComponent<JMenu> {
    public FileMenu(GuiReference guiReference) {
        super(guiReference, new JMenu("File"));

        getComponent().add(new NewFileMenu(guiReference).getComponent());
		getComponent().add(new SaveMenu(guiReference).getComponent());
		getComponent().add(new LoadMenu(guiReference).getComponent());
    }
}

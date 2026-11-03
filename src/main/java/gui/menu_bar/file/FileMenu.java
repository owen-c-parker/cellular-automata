package gui.menu_bar.file;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;

public class FileMenu extends AccessorComponent<JMenu> {
    public FileMenu(GuiReference guiReference) {
        super(guiReference, new JMenu("File"));

        getComponent().add(new NewFileMenu(guiReference).getComponent());
		getComponent().add(new SaveMenu(guiReference).getComponent());
		getComponent().add(new LoadMenu(guiReference).getComponent());
    }
}

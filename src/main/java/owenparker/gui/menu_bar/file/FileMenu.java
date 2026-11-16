package owenparker.gui.menu_bar.file;

import owenparker.gui.context.DisplayContext;

import javax.swing.*;

public class FileMenu extends JMenu {
    public FileMenu() {
        super("File");

        add(new NewFileMenu());
        add(new JSeparator());
        add(new ExportMenu());
		add(new SaveMenu());
		add(new LoadMenu());
        add(new JSeparator());
        add(new PreferencesMenu());
    }
}

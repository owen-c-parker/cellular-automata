package owenparker.gui.menu_bar.file;

import owenparker.gui.ConfigMenuWindow;
import owenparker.gui.preferences.Preferences;

import javax.swing.*;

public class PreferencesMenu extends JMenuItem {
    public PreferencesMenu() {
        super("Preferences");

        addActionListener(_ -> new ConfigMenuWindow(Preferences.getInstance(), "Preferences"));
    }
}

package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.ConfigMenuWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class PreferencesMenu extends AccessorComponent<JMenuItem> {
    public PreferencesMenu(GlobalReference globalReference) {
        super(globalReference, new JMenuItem("Preferences"));

        getComponent().addActionListener(_ -> new ConfigMenuWindow(globalReference.preferences, "Preferences"));
    }
}

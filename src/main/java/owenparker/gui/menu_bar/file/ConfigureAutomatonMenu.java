package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.ConfigMenuWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class ConfigureAutomatonMenu extends AccessorComponent<JMenuItem> {
    public ConfigureAutomatonMenu(GlobalReference globalReference) {
        super(globalReference, new JMenuItem("Configure automaton"));

        getComponent().addActionListener(_ -> new ConfigMenuWindow(globalReference.getAutomaton().getConfig(), "Configure Automaton"));
    }
}

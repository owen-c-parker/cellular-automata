package owenparker.gui.menu_bar.file;

import owenparker.engine.AutomatonFactory;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;
import owenparker.gui.Launcher;

import javax.swing.*;

public class NewFileMenu extends AccessorComponent<JMenu> {
    public NewFileMenu(GuiReference guiReference) {
        super(guiReference, new JMenu("New"));

        generateMenuItems();
    }

    private void generateMenuItems() {
        for (AutomatonFactory factory : Launcher.getAvailableAutomatonClasses()) {
            getComponent().add(generateMenuItem(factory));
        }
    }

    private JMenuItem generateMenuItem(AutomatonFactory factory) {
        JMenuItem menuItem = new JMenuItem(factory.getDisplayableName());
        menuItem.addActionListener(_ -> this.guiReference.setAutomaton(factory.buildAutomaton()));
        return menuItem;
    }
}

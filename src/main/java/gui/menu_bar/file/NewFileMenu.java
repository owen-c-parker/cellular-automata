package gui.menu_bar.file;

import engine.Automaton;
import engine.AutomatonFactory;
import gui.AccessorComponent;
import gui.GuiReference;
import gui.Launcher;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

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
        Automaton instance = factory.buildAutomaton();
        JMenuItem menuItem = new JMenuItem(instance.getDisplayableName());
        menuItem.addActionListener(_ -> this.guiReference.setAutomaton(instance));
        return menuItem;
    }
}

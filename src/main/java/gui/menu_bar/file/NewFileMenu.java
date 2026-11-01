package gui.menu_bar.file;

import engine.Automaton;
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
        for (Class<? extends Automaton> clazz : Launcher.getAvailableAutomatonClasses()) {
            getComponent().add(generateMenuItem(clazz));
        }
    }

    private JMenuItem generateMenuItem(Class<? extends Automaton> clazz) {
        try {
            Automaton instance = (Automaton) clazz.getConstructors()[0].newInstance();
            JMenuItem menuItem = new JMenuItem(instance.getDisplayableName());
            menuItem.addActionListener(_ -> this.guiReference.setAutomaton(instance));
            return menuItem;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

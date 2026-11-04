package owenparker.gui.menu_bar.file;

import owenparker.engine.AutomatonFactory;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.Launcher;
import owenparker.gui.menu_bar.MenuHeader;
import owenparker.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class NewFileMenu extends AccessorComponent<JMenu> {
    public NewFileMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.NEW_MENU_OPTION)));

		generateMenuItems();
    }

    private void generateMenuItems() {
        Launcher.installedPlugins
				.forEach(this::generateMenuItems);
    }

	private void generateMenuItems(Plugin plugin) {
		getComponent().add(new MenuHeader(this.globalReference, plugin.getDisplayableName()).getComponent());
		plugin.getAutomatonFactories().forEach(this::generateMenuItem);
	}

    private void generateMenuItem(AutomatonFactory factory) {
        JMenuItem menuItem = new JMenuItem(factory.getDisplayableName());
        menuItem.addActionListener(_ -> this.globalReference.setAutomaton(factory.buildAutomaton()));
        getComponent().add(menuItem);
    }
}

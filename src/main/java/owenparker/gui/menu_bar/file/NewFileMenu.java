package owenparker.gui.menu_bar.file;

import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.Launcher;
import owenparker.gui.menu_bar.MenuHeader;
import owenparker.gui.menu_bar.PluginResourceLister;
import owenparker.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class NewFileMenu extends AccessorComponent<JMenu> {
    public NewFileMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.NEW_MENU_OPTION)));


		PluginResourceLister.generateList(
				this,
				thing -> globalReference.setAutomaton(thing.buildAutomaton()),
				Plugin::getAutomatonFactories,
				JMenuItem::new
		);
    }
}

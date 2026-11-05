package owenparker.gui.menu_bar.file;

import owenparker.engine.AutomatonFactory;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.menu_bar.PluginResourceMenuLister;
import owenparker.gui.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class NewFileMenu extends AccessorComponent<JMenu> {
    public NewFileMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.NEW_MENU_OPTION)));

		PluginResourceMenuLister<AutomatonFactory> lister = new PluginResourceMenuLister<>(
				Plugin::getAutomatonFactories,
				factory -> globalReference.setAutomaton(factory.buildAutomaton())
		);

		lister.listResources(this);
    }
}

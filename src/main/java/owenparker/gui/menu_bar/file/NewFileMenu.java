package owenparker.gui.menu_bar.file;

import owenparker.engine.AutomatonFactory;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.SimulationContext;
import owenparker.gui.menu_bar.PluginResourceMenuLister;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class NewFileMenu extends JMenu {
    public NewFileMenu() {
        super("New");

		PluginResourceMenuLister<AutomatonFactory<?>> lister = new PluginResourceMenuLister<>(
				Plugin::getAutomatonFactories,
				SimulationContext::buildAutomatonFromFactory
		);

		lister.listResources(this);
    }
}

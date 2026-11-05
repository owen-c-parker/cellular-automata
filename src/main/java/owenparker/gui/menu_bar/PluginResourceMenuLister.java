package owenparker.gui.menu_bar;

import owenparker.engine.HasDisplayableName;
import owenparker.gui.AccessorComponent;
import owenparker.gui.Launcher;
import owenparker.plugins.Plugin;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class PluginResourceMenuLister<T extends HasDisplayableName> {

	private final Function<Plugin, List<T>> resourcesGetter;
	private Callable<AbstractButton> buttonGetter = JMenuItem::new;
	private final Consumer<T> onClick;

	public PluginResourceMenuLister(Function<Plugin, List<T>> resourcesGetter, Consumer<T> onClick) {
		super();
		this.resourcesGetter = resourcesGetter;
		this.onClick = onClick;
	}

	public void setButtonGetter(Callable<AbstractButton> buttonGetter) {
		this.buttonGetter = buttonGetter;
	}

	public void listResources(AccessorComponent<JMenu> menuToAddTo) {
		Launcher.installedPlugins.forEach(plugin -> {
			if (this.resourcesGetter.apply(plugin).isEmpty()) return;
			menuToAddTo.getComponent().add(new MenuHeader(menuToAddTo.globalReference, plugin.getDisplayableName()).getComponent());
			ResourceMenuLister<T> lister = new ResourceMenuLister<>(this.onClick, this.resourcesGetter.apply(plugin));
			lister.setButtonGetter(this.buttonGetter);
			lister.listResources(menuToAddTo);
		});
	}
}

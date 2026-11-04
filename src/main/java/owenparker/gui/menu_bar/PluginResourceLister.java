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

public class PluginResourceLister {
	public static <T extends HasDisplayableName> void generateList(AccessorComponent<JMenu> menu, Consumer<T> onClick, Function<Plugin, List<T>> resourcesGetter, Callable<AbstractButton> buttonTypeGetter) {
		Launcher.installedPlugins.forEach(plugin -> {
			if (resourcesGetter.apply(plugin).isEmpty())
				return;

			menu.getComponent().add(new MenuHeader(menu.globalReference, plugin.getDisplayableName()).getComponent());
			PluginResourceLister.generatePluginSublist(plugin, menu, onClick, resourcesGetter, buttonTypeGetter);
		});
	}

	private static <T extends HasDisplayableName> void generatePluginSublist(
			Plugin plugin,
			AccessorComponent<JMenu> menu,
			Consumer<T> onClick, Function<Plugin, List<T>> resourcesGetter,
			Callable<AbstractButton> buttonTypeGetter
	) {
		List<T> resources = resourcesGetter.apply(plugin);
		for (T resource : resources) {
			try {
				AbstractButton button = buttonTypeGetter.call();
				button.setText(resource.getDisplayableName());
				button.addActionListener(_ -> onClick.accept(resource));

				menu.getComponent().add(button);
			} catch (Exception e) {
				Launcher.errorWindow(e);
			}
		}
	}
}

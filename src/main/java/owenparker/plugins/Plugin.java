package owenparker.plugins;

import owenparker.engine.AutomatonFactory;
import owenparker.engine.HasDisplayableName;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.Launcher;

import java.util.Collection;

public abstract class Plugin implements HasDisplayableName {
	public abstract Collection<AutomatonFactory> getAutomatonFactories();
	public abstract Collection<ColorPalette> getColorPalettes();
	public abstract String getAuthor();

	public final void load() {
		Launcher.automatonFactoriesFromPlugins.addAll(getAutomatonFactories());
		Launcher.colorPalettesFromPlugins.addAll(getColorPalettes());
		Launcher.installedPlugins.add(this);

		System.out.printf("Loaded plugin \"%s\"\n", getDisplayableName());
	}
}

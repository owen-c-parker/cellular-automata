package owenparker.plugins;

import owenparker.engine.AutomatonFactory;
import owenparker.engine.HasDisplayableName;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.Launcher;

import java.util.List;

public abstract class Plugin implements HasDisplayableName {
	public List<AutomatonFactory<?>> getAutomatonFactories() {
		return List.of();
	}

	public List<ColorPalette> getColorPalettes() {
		return List.of();
	}

	public abstract String getAuthor();

	public final void load() {
		Launcher.installedPlugins.add(this);
	}
}

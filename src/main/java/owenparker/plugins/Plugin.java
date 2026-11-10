package owenparker.plugins;

import owenparker.engine.AutomatonFactory;
import owenparker.engine.HasDisplayableName;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.Launcher;

import java.util.List;

public abstract class Plugin implements HasDisplayableName {
	/**
	 * @return list of {@link AutomatonFactory AutomatonFactories} defined by this{@link Plugin}
	 */
	public List<AutomatonFactory<?>> getAutomatonFactories() {
		return List.of();
	}

	/**
	 * @return list of {@link ColorPalette ColorPalettes} defined by this {@link Plugin}
	 */
	public List<ColorPalette> getColorPalettes() {
		return List.of();
	}

	/**
	 * @return author of the {@link Plugin}
	 */
	public abstract String getAuthor();

	public final void load() {
		Launcher.installedPlugins.add(this);
	}
}

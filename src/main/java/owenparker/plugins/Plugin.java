package owenparker.plugins;

import owenparker.engine.AutomatonFactory;
import owenparker.engine.HasDisplayableName;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.Launcher;
import owenparker.lang.EnglishUnitedStates;
import owenparker.lang.Language;

import java.util.Collection;
import java.util.List;

public abstract class Plugin implements HasDisplayableName {
	public List<AutomatonFactory> getAutomatonFactories() {
		return List.of();
	}

	public List<ColorPalette> getColorPalettes() {
		return List.of();
	}

	public List<Language> getLanguages() {
		return List.of();
	}

	public abstract String getAuthor();

	public final void load() {
		Launcher.installedPlugins.add(this);

		System.out.printf("Loaded plugin \"%s\"\n", getDisplayableName());
	}

	protected String translate(Language.Key key) {
		return Language.currentLanguage.get(key);
	}
}

package owenparker.gui.lang;

import owenparker.engine.HasDisplayableName;
import owenparker.gui.GenericWindow;

import java.util.Map;

public abstract class Language implements HasDisplayableName {

	private static Language currentLanguage;

	public static Language getCurrentLanguage() {
		return currentLanguage;
	}

	public static void changeLanguage(Language language) {
		forceCurrentLanguage(language);
	}

	public static void forceCurrentLanguage(Language language) {
		currentLanguage = language;
	}

	public enum Key {
		TITLE("Cellular Automaton Simulator"),

		ABOUT_MENU_OPTION("About"),
		LICENSE_MENU_OPTION("License information"),
		FILE_MENU_OPTION("File"),
		NEW_MENU_OPTION("New"),
		LOAD_MENU_OPTION("Load automaton state from file"),
		SAVE_MENU_OPTION("Save automaton state to file"),
		PALETTES_MENU_OPTION("Palettes"),
		PLACEMENT_MENU_OPTION("Place"),
		TOOLS_MENU_OPTION("Tools"),
		CLEAR_MENU_OPTION("Clear entire grid"),
		NOISE_MENU_ITEM("Fill screen with random cells"),
		CELL_MENU_OPTION("Cell"),
		AGENT_MENU_OPTION("Agent"),
		COLOR_SCHEME_MENU_OPTION("Color Scheme"),
		LANGUAGE_MENU_OPTION("Language"),

		DARK_THEME("Dark"),
		LIGHT_THEME("Light"),
		DARK_CONTRAST_THEME("Dark (high contrast)"),
		LIGHT_CONTRAST_THEME("Light (high contrast)"),
		DARK_ROAST_THEME("Dark roast"),
		LIGHT_ROAST_THEME("Light roast"),

		DEFAULT_PLUGIN_NAME("Default"),

		STAT_ACTIVE_CELLS("Active cells: "),
		STAT_ZOOM("Zoom level: "),

		FILE_TYPE_DESCRIPTION("Automaton state"),
		NO_FILE_SELECTED("No file selected"),

		ERROR_WINDOW_TITLE("Error - %s"),

		STEPS_PER_SECOND("steps/s")

		;



		private final String defaultString;

		Key(String defaultString) {
			this.defaultString = defaultString;
		}

		public String getDefault() {
			return this.defaultString;
		}
	}

	public String get(Key key) {
		return getMap().getOrDefault(key, key.getDefault());
	}

	protected abstract Map<Key, String> getMap();

	public abstract String getCode();
}

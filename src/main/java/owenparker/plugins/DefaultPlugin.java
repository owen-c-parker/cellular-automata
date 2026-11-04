package owenparker.plugins;

import owenparker.automaton_factories.*;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.lang.EnglishUnitedStates;
import owenparker.gui.lang.Language;

import java.util.List;
import java.util.Map;

import static java.awt.Color.BLACK;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.GRAY;
import static java.awt.Color.LIGHT_GRAY;
import static java.awt.Color.WHITE;
import static java.awt.Color.decode;

public class DefaultPlugin extends Plugin {
	@Override
	public List<AutomatonFactory> getAutomatonFactories() {
		return List.of(
				new CGOLFactory(),
				new KnightCGOLFactory(),
				new Rule110Factory(),
				new WireworldFactory(),
				new BriansBrainFactory(),
				new LangtonsAntFactory()
		);
	}

	@Override
	public List<ColorPalette> getColorPalettes() {
		return List.of(new ColorPalette(translate(Language.Key.DARK_THEME), Map.of()),
				new ColorPalette(translate(Language.Key.DARK_CONTRAST_THEME), Map.of(
						ColorPalette.Slot.BACKGROUND, BLACK,
						ColorPalette.Slot.FOREGROUND, WHITE,
						ColorPalette.Slot.GRIDLINES, GRAY
				)),
				new ColorPalette(translate(Language.Key.LIGHT_THEME), Map.of(
						ColorPalette.Slot.BACKGROUND, LIGHT_GRAY,
						ColorPalette.Slot.FOREGROUND, DARK_GRAY,
						ColorPalette.Slot.GRIDLINES, GRAY
				)),
				new ColorPalette(translate(Language.Key.LIGHT_CONTRAST_THEME), Map.of(
						ColorPalette.Slot.BACKGROUND, WHITE,
						ColorPalette.Slot.FOREGROUND, BLACK,
						ColorPalette.Slot.GRIDLINES, GRAY
				)),
				new ColorPalette(translate(Language.Key.LIGHT_ROAST_THEME), Map.of(
						ColorPalette.Slot.BACKGROUND, decode("#cbaf76"),
						ColorPalette.Slot.MIDDLE, decode("#816E49"),
						ColorPalette.Slot.FOREGROUND, decode("#413725"),
						ColorPalette.Slot.ACTIVE_1, decode("#bd6c00"),
						ColorPalette.Slot.DISSIPATING_1, decode("#4b2c00"),
						ColorPalette.Slot.ACTIVE_2, decode("#00bd36"),
						ColorPalette.Slot.DISSIPATING_2, decode("#00651d")
				)),
				new ColorPalette(translate(Language.Key.DARK_ROAST_THEME), Map.of(
						ColorPalette.Slot.BACKGROUND, decode("#413725"),
						ColorPalette.Slot.MIDDLE, decode("#816E49"),
						ColorPalette.Slot.FOREGROUND, decode("#cbaf76"),
						ColorPalette.Slot.ACTIVE_1, decode("#bd6c00"),
						ColorPalette.Slot.DISSIPATING_1, decode("#4b2c00"),
						ColorPalette.Slot.ACTIVE_2, decode("#00bd36"),
						ColorPalette.Slot.DISSIPATING_2, decode("#00651d")
				)));
	}

	@Override
	public List<Language> getLanguages() {
		return List.of(new EnglishUnitedStates());
	}

	@Override
	public String getAuthor() {
		return "Owen Parker";
	}

	@Override
	public String getDisplayableName() {
		return translate(Language.Key.DEFAULT_PLUGIN_NAME);
	}
}

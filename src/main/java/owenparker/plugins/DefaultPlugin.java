package owenparker.plugins;

import owenparker.automaton_factories.*;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.colors.ColorPalette;

import java.util.List;
import java.util.Map;

import static java.awt.Color.*;

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
		return List.of(
				new ColorPalette("Dark Roast", Map.of(
						ColorPalette.Slot.BACKGROUND, decode("#413725"),
						ColorPalette.Slot.MIDDLE, decode("#816E49"),
						ColorPalette.Slot.FOREGROUND, decode("#cbaf76"),
						ColorPalette.Slot.ACTIVE_1, decode("#bd6c00"),
						ColorPalette.Slot.DISSIPATING_1, decode("#4b2c00"),
						ColorPalette.Slot.ACTIVE_2, decode("#00bd36"),
						ColorPalette.Slot.DISSIPATING_2, decode("#00651d"),
						ColorPalette.Slot.GRIDLINES, decode("#15100d")
				)),
				new ColorPalette("Light Roast", Map.of(
						ColorPalette.Slot.BACKGROUND, decode("#cbaf76"),
						ColorPalette.Slot.MIDDLE, decode("#816E49"),
						ColorPalette.Slot.FOREGROUND, decode("#413725"),
						ColorPalette.Slot.ACTIVE_1, decode("#bd6c00"),
						ColorPalette.Slot.DISSIPATING_1, decode("#4b2c00"),
						ColorPalette.Slot.ACTIVE_2, decode("#00bd36"),
						ColorPalette.Slot.DISSIPATING_2, decode("#00651d"),
						ColorPalette.Slot.GRIDLINES, decode("#15100d")
				)),
				new ColorPalette("Dark", Map.of()),
				new ColorPalette("Dark (high contrast)", Map.of(
						ColorPalette.Slot.BACKGROUND, BLACK,
						ColorPalette.Slot.FOREGROUND, WHITE,
						ColorPalette.Slot.GRIDLINES, GRAY
				)),
				new ColorPalette("Light", Map.of(
						ColorPalette.Slot.BACKGROUND, LIGHT_GRAY,
						ColorPalette.Slot.FOREGROUND, DARK_GRAY,
						ColorPalette.Slot.GRIDLINES, GRAY
				)),
				new ColorPalette("Light (high contrast)", Map.of(
						ColorPalette.Slot.BACKGROUND, WHITE,
						ColorPalette.Slot.FOREGROUND, BLACK,
						ColorPalette.Slot.GRIDLINES, GRAY
				))
			);
	}

	@Override
	public String getAuthor() {
		return "Owen Parker";
	}

	@Override
	public String getDisplayableName() {
		return "Default";
	}
}

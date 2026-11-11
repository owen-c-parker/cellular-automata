package owenparker.plugins;

import owenparker.plugins.default_content.automaton_factories.*;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.colors.ColorPalette;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.awt.Color.*;

public class DefaultPlugin extends Plugin {
	@Override
	public List<AutomatonFactory<?>> getAutomatonFactories() {
		return List.of(
				new CGOLFactory(),
				new LifeLikeFactory(),
				new KnightCGOLFactory(),
				new Rule110Factory(),
				new ElementaryFactory(),
				new WireworldFactory(),
				new BriansBrainFactory(),
				new LangtonsAntFactory()
		);
	}

	private ColorPalette createColorPalette(String title, Map<ColorPalette.Slot, Color> colorMap, Function<Color, Color> indexedColorFunction) {
		return new ColorPalette() {
			@Override
			protected Map<Slot, Color> getColorValues() {
				return colorMap;
			}

			@Override
			public String getDisplayableName() {
				return title;
			}

			@Override
			public Color getIndexedColor(int index) {
				return indexedColorFunction.apply(super.getIndexedColor(index));
			}
		};
	}

	@Override
	public List<ColorPalette> getColorPalettes() {
		return List.of(
				createColorPalette("Dark Roast", Map.of(
						ColorPalette.Slot.BACKGROUND, decode("#413725"),
						ColorPalette.Slot.MIDDLE, decode("#816E49"),
						ColorPalette.Slot.FOREGROUND, decode("#cbaf76"),
						ColorPalette.Slot.ACTIVE_1, decode("#bd6c00"),
						ColorPalette.Slot.DISSIPATING_1, decode("#4b2c00"),
						ColorPalette.Slot.ACTIVE_2, decode("#00bd36"),
						ColorPalette.Slot.DISSIPATING_2, decode("#00651d"),
						ColorPalette.Slot.GRIDLINES, decode("#15100d")
				), Color::darker),
				createColorPalette("Light Roast", Map.of(
						ColorPalette.Slot.BACKGROUND, decode("#cbaf76"),
						ColorPalette.Slot.MIDDLE, decode("#816E49"),
						ColorPalette.Slot.FOREGROUND, decode("#413725"),
						ColorPalette.Slot.ACTIVE_1, decode("#bd6c00"),
						ColorPalette.Slot.DISSIPATING_1, decode("#4b2c00"),
						ColorPalette.Slot.ACTIVE_2, decode("#00bd36"),
						ColorPalette.Slot.DISSIPATING_2, decode("#00651d"),
						ColorPalette.Slot.GRIDLINES, decode("#15100d")
				), Color::brighter),
				createColorPalette("Dark", Map.of(), Color::darker),
				createColorPalette("Dark (high contrast)", Map.of(
						ColorPalette.Slot.BACKGROUND, BLACK,
						ColorPalette.Slot.FOREGROUND, WHITE,
						ColorPalette.Slot.GRIDLINES, GRAY
				), color -> color),
				createColorPalette("Light", Map.of(
						ColorPalette.Slot.BACKGROUND, LIGHT_GRAY,
						ColorPalette.Slot.FOREGROUND, DARK_GRAY,
						ColorPalette.Slot.GRIDLINES, GRAY
				), Color::brighter),
				createColorPalette("Light (high contrast)", Map.of(
						ColorPalette.Slot.BACKGROUND, WHITE,
						ColorPalette.Slot.FOREGROUND, BLACK,
						ColorPalette.Slot.GRIDLINES, GRAY
				), color -> color)
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

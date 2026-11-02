package engine.colors;

import engine.HasDisplayableName;

import java.awt.*;
import java.util.Map;

import static java.awt.Color.*;

public class ColorPalette implements HasDisplayableName {

	private final String name;

	@Override
	public String getDisplayableName() {
		return this.name;
	}

	public enum DefaultPaletteEntry {
		DARK_MODE("Dark", Map.of()),
		DARK_MODE_HIGH_CONTRAST("Dark (high contrast)", Map.of(
				Slot.BACKGROUND, BLACK,
				Slot.FOREGROUND, WHITE,
				Slot.GRIDLINES, GRAY
		)),
		LIGHT_MODE("Light", Map.of(
				Slot.BACKGROUND, LIGHT_GRAY,
				Slot.FOREGROUND, DARK_GRAY,
				Slot.GRIDLINES, GRAY
		)),
		LIGHT_MODE_HIGH_CONTRAST("Light (high contrast)", Map.of(
				Slot.BACKGROUND, WHITE,
				Slot.FOREGROUND, BLACK,
				Slot.GRIDLINES, GRAY
		));

		private final ColorPalette palette;

		DefaultPaletteEntry(String name, Map<Slot, Color> map) {
			this.palette = new ColorPalette(name, map);
		}

		public ColorPalette getPalette() {
			return this.palette;
		}
	}


	private final Map<Slot, Color> colorValues;

	public ColorPalette(String name, Map<Slot, Color> colorValues) {
		this.name = name;
		this.colorValues = Map.copyOf(colorValues);
	}

	public enum Slot {
		BACKGROUND(DARK_GRAY),
		MIDDLE(GRAY),
		FOREGROUND(LIGHT_GRAY),
		ACTIVE_1(RED),
		DISSIPATING_1(RED.darker()),
		ACTIVE_2(BLUE),
		DISSIPATING_2(BLUE.darker()),
		GRIDLINES(BLACK);

		public final Color defaultColor;

		Slot(Color defaultColor) {
			this.defaultColor = defaultColor;
		}
	}

	public Color getColor(Slot slot) {
		return this.colorValues.getOrDefault(slot, slot.defaultColor);
	}
}

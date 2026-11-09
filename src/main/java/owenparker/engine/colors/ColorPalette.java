package owenparker.engine.colors;

import owenparker.engine.HasDisplayableName;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

import static java.awt.Color.*;

public class ColorPalette implements HasDisplayableName, Serializable {

	private final String name;

	@Override
	public String getDisplayableName() {
		return this.name;
	}

    @Override
    public String toString() {
        return getDisplayableName();
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

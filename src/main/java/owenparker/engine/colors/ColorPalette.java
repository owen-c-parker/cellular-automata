package owenparker.engine.colors;

import owenparker.engine.HasDisplayableName;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;

public abstract class ColorPalette implements HasDisplayableName, Serializable {

	private final static float PHI = (float) (1.0 + Math.sqrt(5.0) / 2.0);

    @Override
    public String toString() {
        return getDisplayableName();
    }

	/**
	 * Get a unique color based on an index. By default, it returns a full saturation color whose hue is
	 * <code>360 * index * &phi;</code>, but it is encouraged that you override this behavior. A good option
	 * is to call this method and modify the resulting color.
	 */
	public Color getIndexedColor(int index) {
		float angle = ((float) index) * 360.0f * PHI;

		Color.getHSBColor(angle, 1.0f, 0.5f);

		return null;
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
		return getColorValues().getOrDefault(slot, slot.defaultColor);
	}

	protected abstract Map<Slot, Color> getColorValues();
}

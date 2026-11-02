package engine.colors;

import java.awt.*;

public class PaletteColor implements ColorEvaluator {

	private final ColorPalette.Slot slot;

	public PaletteColor(ColorPalette.Slot slot) {
		this.slot = slot;
	}

	@Override
	public Color getColor(ColorPalette palette) {
		return palette.getColor(this.slot);
	}
}

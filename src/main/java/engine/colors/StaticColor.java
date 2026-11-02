package engine.colors;

import java.awt.*;

public class StaticColor implements ColorEvaluator {

	private final Color color;

	public StaticColor(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor(ColorPalette palette) {
		return this.color;
	}
}

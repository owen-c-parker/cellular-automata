package owenparker.engine.colors;

import java.awt.*;

/**
 * A color that is unique for a given index
 */
public class IndexedColor implements ColorEvaluator {
	private final int index;

	public IndexedColor(int index) {
		this.index = index;
	}

	@Override
	public Color getColor(ColorPalette palette) {
		return palette.getIndexedColor(this.index);
	}
}

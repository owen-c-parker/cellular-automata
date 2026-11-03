package engine;

import engine.colors.ColorEvaluator;
import engine.colors.ColorPalette;
import engine.colors.PaletteColor;
import engine.colors.StaticColor;

import java.awt.*;
import java.io.Serializable;

public abstract class State implements CursorPlaceable, HasDisplayableName, Serializable {
	private final String name;
	private final ColorEvaluator color;

	public State(ColorEvaluator color, String name) {
		this.color = color;
		this.name = name;
	}

	public State(Color color, String name) {
		this(new StaticColor(color), name);
	}

	public State(ColorPalette.Slot slot, String name) {
		this(new PaletteColor(slot), name);
	}

	public abstract State getNewState(Board board, OrderedPair location);

	public final ColorEvaluator getColorEvaluator() {
		return this.color;
	}

	public final String getDisplayableName() {
		return this.name;
	}

	/**
	 * Override this method to draw additional graphics over the cell. The origin is the top left of the cell, and the
	 * clipping bounds are bound to the cell itself
	 * @param g Graphics context
	 */
	public void customDraw(Graphics g) {}
}

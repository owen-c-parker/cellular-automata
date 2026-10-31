package engine;

import java.awt.*;

public abstract class State implements CursorPlaceable {
	private final String name;
	private final Color color;

	public State(Color color, String name) {
		this.color = color;
		this.name = name;
	}

	public abstract State getNewState(Board board, OrderedPair location);

	public final Color getColor() {
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

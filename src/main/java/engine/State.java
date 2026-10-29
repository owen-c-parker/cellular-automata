package engine;

import java.awt.*;

public abstract class State {
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
}

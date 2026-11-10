package owenparker.engine;

import owenparker.engine.colors.ColorPalette;

import java.awt.*;
import java.io.Serializable;

public interface AgentFactory extends CursorPlaceable, HasDisplayableName, Serializable {

	/**
	 * Create an agent
	 * @param position starting place
	 * @return new agent
	 */
    Agent buildAgent(OrderedPair position);

	/**
	 * Draw an instance of an agent that this factory creates
	 * @param g graphics
	 * @param colorPalette colors to use for drawing
	 */
	@Override
	default void draw(Graphics g, ColorPalette colorPalette) {
		buildAgent(OrderedPair.ORIGIN).draw(g);
	}
}

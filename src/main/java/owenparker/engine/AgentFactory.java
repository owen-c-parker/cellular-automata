package owenparker.engine;

import owenparker.engine.colors.ColorPalette;

import java.awt.*;
import java.io.Serializable;

public interface AgentFactory extends CursorPlaceable, HasDisplayableName, Serializable {
    Agent buildAgent(OrderedPair position);

	@Override
	default void draw(Graphics g, ColorPalette colorPalette) {
		buildAgent(OrderedPair.ORIGIN).draw(g);
	}
}

package owenparker.engine;

import owenparker.engine.colors.ColorPalette;

import java.awt.*;

public interface CursorPlaceable extends HasDisplayableName {
	void draw(Graphics g, ColorPalette colorPalette);
}

package engine.colors;

import java.awt.*;
import java.io.Serializable;

public interface ColorEvaluator extends Serializable {
	Color getColor(ColorPalette palette);
}

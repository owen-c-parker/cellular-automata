package owenparker.gui.grid.gridlines;

import owenparker.engine.OrderedPair;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.GlobalReference;
import owenparker.gui.grid.GridDisplay;

import java.awt.*;

public class SolidGridlines extends Gridlines {
	@Override
	public String getDisplayableName() {
		return "Solid";
	}


	@Override
	public void draw(Graphics g, GridDisplay gridDisplay) {
		int sizeOfLines = (int) Math.clamp(4 + (30 / gridDisplay.getCellScale()), 0, 3);

		GlobalReference globalReference = gridDisplay.globalReference;

		OrderedPair screenSizeInPixels = gridDisplay.getScreenSizeInPixels();
		g.setColor(globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));
		for (double x = gridDisplay.scrollX.doubleValue() % gridDisplay.getCellScale(); x < screenSizeInPixels.x().intValue(); x += gridDisplay.getCellScale())
			g.fillRect((int) x - 1, 0, sizeOfLines, screenSizeInPixels.y().intValue());
		for (double y = gridDisplay.scrollY.doubleValue() % gridDisplay.getCellScale(); y < screenSizeInPixels.y().intValue(); y += gridDisplay.getCellScale())
			g.fillRect(0, (int) y - 1, screenSizeInPixels.x().intValue(), sizeOfLines);
	}
}

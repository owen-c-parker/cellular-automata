package owenparker.gui.grid.gridlines;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.grid.GridDisplay;

import java.awt.*;

public class DotsGridlines extends Gridlines {
	@Override
	public String getDisplayableName() {
		return "Dots";
	}

	@Override
	public void draw(Graphics g, GridDisplay gridDisplay) {
		int size = 4;

		gridDisplay.getCellLocationsOnScreen()
				.stream()
				.map(gridDisplay::fromCellSpaceToScreenSpace)
				.forEach(location -> {
					g.setColor(gridDisplay.globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES));
					g.fillRect(
							location.xInt() - (size / 2),
							location.yInt() - (size / 2),
							size,
							size
					);
				});
	}
}

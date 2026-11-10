package owenparker.gui.grid;

import java.awt.event.MouseEvent;

public class FillRectangleOperation extends RectangleAreaOperation {
	public FillRectangleOperation(GridDisplay gridDisplay) {
		super(gridDisplay);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);

		getCellLocationsInBounds().forEach(location -> getAutomaton().place(location, getGlobalReference().selectedForPlacement));
		getGridDisplay().renderBounds(null);
		getGlobalReference().repaintEvent.fire();
	}
}

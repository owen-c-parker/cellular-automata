package owenparker.gui.grid;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;

import java.awt.event.MouseEvent;

public class FillRectangleOperation extends RectangleAreaOperation {
	public FillRectangleOperation(GridDisplay gridDisplay) {
		super(gridDisplay);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);

		getCellLocationsInBounds().forEach(location -> getAutomaton().place(location, DisplayContext.selectedForPlacement));
		getGridDisplay().renderBounds(null);
		Events.repaint.fire();
	}
}

package owenparker.gui.grid;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PencilOperation extends CursorModeOperation {
	public PencilOperation(GridDisplay gridDisplay) {
		super(gridDisplay);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		OrderedPair location = getGridDisplay().fromScreenSpaceToCellSpace(new OrderedPair(e));

		if (SwingUtilities.isLeftMouseButton(e))
			getAutomaton().place(location, DisplayContext.selectedForPlacement);
		if (SwingUtilities.isRightMouseButton(e))
			getAutomaton().remove(location, DisplayContext.selectedForPlacement);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);

		OrderedPair location = getGridDisplay().fromScreenSpaceToCellSpace(new OrderedPair(e));
		if (DisplayContext.selectedForPlacement instanceof State state) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				getAutomaton().place(location, state);
				Events.repaint.fire();
			}
			if (SwingUtilities.isRightMouseButton(e)) {
				getAutomaton().remove(location, state);
				Events.repaint.fire();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
}

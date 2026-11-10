package owenparker.gui.grid;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;

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
			getAutomaton().place(location, getGlobalReference().selectedForPlacement);
		if (SwingUtilities.isRightMouseButton(e))
			getAutomaton().remove(location, getGlobalReference().selectedForPlacement);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);

		OrderedPair location = getGridDisplay().fromScreenSpaceToCellSpace(new OrderedPair(e));
		if (getGlobalReference().selectedForPlacement instanceof State state) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				getAutomaton().place(location, state);
				getGlobalReference().repaintEvent.fire();
			}
			if (SwingUtilities.isRightMouseButton(e)) {
				getAutomaton().remove(location, state);
				getGlobalReference().repaintEvent.fire();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
}

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
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		System.out.println(e);

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
			if (SwingUtilities.isLeftMouseButton(e))
				getAutomaton().place(location, state);
			if (SwingUtilities.isRightMouseButton(e))
				getAutomaton().remove(location, state);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
}

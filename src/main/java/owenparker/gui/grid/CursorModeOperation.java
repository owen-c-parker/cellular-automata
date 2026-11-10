package owenparker.gui.grid;

import owenparker.engine.Automaton;
import owenparker.engine.OrderedPair;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.awt.event.*;

public abstract class CursorModeOperation implements MouseListener, MouseMotionListener, MouseWheelListener {
	private final GridDisplay gridDisplay;

	OrderedPair lastMiddleClickedLocation = OrderedPair.ORIGIN;
	OrderedPair lastMiddleClickDraggedLocation = OrderedPair.ORIGIN;
	private boolean lastEventWasMiddleDrag = false;


	protected GlobalReference getGlobalReference() {
		return this.gridDisplay.globalReference;
	}

	protected Automaton getAutomaton() {
		return this.gridDisplay.globalReference.getAutomaton();
	}

	public CursorModeOperation(GridDisplay gridDisplay) {
		this.gridDisplay = gridDisplay;
	}

	protected GridDisplay getGridDisplay() {
		return this.gridDisplay;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.lastEventWasMiddleDrag = false;

		if (SwingUtilities.isMiddleMouseButton(e))
			this.lastMiddleClickedLocation = new OrderedPair(e);

		getGlobalReference().repaintEvent.fire();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isMiddleMouseButton(e)) {
			OrderedPair location = new OrderedPair(e);

			OrderedPair difference = this.lastMiddleClickDraggedLocation.subtract(location);
			this.lastMiddleClickDraggedLocation = location;

			if (this.lastEventWasMiddleDrag) {
				getGridDisplay().scrollX -= difference.x();
				getGridDisplay().scrollY -= difference.y();
			}

			this.lastEventWasMiddleDrag = true;

			getGlobalReference().repaintEvent.fire();
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		this.lastEventWasMiddleDrag = false;
		this.gridDisplay.performZoom(e);
		getGlobalReference().repaintEvent.fire();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.lastEventWasMiddleDrag = false;
		getGlobalReference().repaintEvent.fire();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}

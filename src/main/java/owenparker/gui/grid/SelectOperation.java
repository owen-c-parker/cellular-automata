package owenparker.gui.grid;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Collection;

public class SelectOperation extends RectangleAreaOperation {
	public SelectOperation(GridDisplay gridDisplay) {
		super(gridDisplay);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		if (SwingUtilities.isRightMouseButton(e))
			copyTo(e);
	}

	private void copyTo(MouseEvent destinationTopLeft) {
		copyTo(getGridDisplay().fromScreenSpaceToCellSpace(new OrderedPair(destinationTopLeft)));
	}

	private void copyTo(OrderedPair destinationTopLeft) {
		Collection<OrderedPair> sourceLocations = getCellLocationsInBounds();
		sourceLocations.forEach(source -> {
			OrderedPair destination = source.subtract(getBounds().getTopLeft()).add(destinationTopLeft);
			State state = getAutomaton().getCellAt(source);
			getAutomaton().setCellAt(destination, state);
		});
	}
}

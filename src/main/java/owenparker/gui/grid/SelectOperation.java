package owenparker.gui.grid;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;

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

		HashMap<OrderedPair, State> buffer = new HashMap<>();
		sourceLocations.forEach(source -> {
			OrderedPair destination = source.subtract(getBounds().getTopLeft()).add(destinationTopLeft);
			buffer.put(destination, getAutomaton().getCellAt(source));
		});
		buffer.forEach((location, state) -> {
			getAutomaton().setCellAt(location, state);
		});
	}
}

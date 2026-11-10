package owenparker.gui.grid;

import owenparker.engine.Bounds;
import owenparker.engine.OrderedPair;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class RectangleAreaOperation extends CursorModeOperation {
	private Bounds bounds = new Bounds(OrderedPair.ORIGIN, OrderedPair.ORIGIN);

	public RectangleAreaOperation(GridDisplay gridDisplay) {
		super(gridDisplay);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		if (!SwingUtilities.isLeftMouseButton(e))
			return;

		OrderedPair location = getGridDisplay().fromScreenSpaceToCellSpace(new OrderedPair(e));

		this.bounds = new Bounds(location, location);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);

		if (!SwingUtilities.isLeftMouseButton(e))
			return;

		OrderedPair location = getGridDisplay().fromScreenSpaceToCellSpace(new OrderedPair(e));

		this.bounds = new Bounds(this.bounds.a(), location);

		getGridDisplay().renderBounds(this.bounds);
	}

	protected Collection<OrderedPair> getCellLocationsInBounds() {
		Set<OrderedPair> locations = new HashSet<>();
		for (int y = this.bounds.getTopLeft().y(); y <= this.bounds.getBottomRight().y(); y++) {
			for (int x = this.bounds.getTopLeft().x(); x <= this.bounds.getBottomRight().x(); x++) {
				locations.add(new OrderedPair(x, y));
			}
		}
		return locations;
	}

	protected Bounds getBounds() {
		return this.bounds;
	}
}

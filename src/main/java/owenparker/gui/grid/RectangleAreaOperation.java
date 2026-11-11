package owenparker.gui.grid;

import owenparker.engine.Bounds;
import owenparker.engine.OrderedPair;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
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
		getGlobalReference().repaintEvent.fire();
	}

	protected Collection<OrderedPair> getCellLocationsInBounds() {
		Set<OrderedPair> locations = new HashSet<>();
		for (BigInteger y = this.bounds.getTopLeft().y(); y.compareTo(this.bounds.getBottomRight().y()) <= 0; y = y.add(BigInteger.ONE)) {
			for (BigInteger x = this.bounds.getTopLeft().x(); x.compareTo(this.bounds.getBottomRight().x()) <= 0; x = x.add(BigInteger.ONE)) {
				locations.add(new OrderedPair(x, y));
			}
		}
		return locations;
	}

	protected Bounds getBounds() {
		return this.bounds;
	}
}

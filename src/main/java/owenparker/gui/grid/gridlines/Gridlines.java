package owenparker.gui.grid.gridlines;

import owenparker.engine.HasDisplayableName;
import owenparker.gui.grid.GridDisplay;

import java.awt.*;

public abstract class Gridlines implements HasDisplayableName {
	@Override
	public boolean equals(Object obj) {
		return getDisplayableName().equals(obj.toString());
	}

	@Override
	public String toString() {
		return getDisplayableName();
	}

	public abstract void draw(Graphics g, GridDisplay gridDisplay);
}

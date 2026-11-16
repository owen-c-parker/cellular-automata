package owenparker.gui.sidebar;

import owenparker.engine.CursorPlaceable;
import owenparker.gui.context.DisplayContext;

import java.awt.*;

public class PlaceableButton extends SideBarButton {

	private final CursorPlaceable cursorPlaceable;

	public PlaceableButton(CursorPlaceable cursorPlaceable, SideBar sideBar) {
		super(sideBar);

		this.cursorPlaceable = cursorPlaceable;

		setToolTipText(cursorPlaceable.getDisplayableName());

		addActionListener(_ -> {
			DisplayContext.selectedForPlacement = cursorPlaceable;
		});
	}

	@Override
	public void refreshSelection() {
		if (DisplayContext.selectedForPlacement.equals(this.cursorPlaceable))
			setBorder(getSelectedBorder());
		else
			setBorder(getDeelectedBorder());
	}

	@Override
	protected void draw(Graphics g) {
		this.cursorPlaceable.draw(g, DisplayContext.getColorPalette());
	}
}

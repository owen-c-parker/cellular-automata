package owenparker.gui.sidebar;

import owenparker.engine.CursorPlaceable;
import owenparker.gui.GlobalReference;

import java.awt.*;

public class PlaceableButton extends SideBarButton {

	private final CursorPlaceable cursorPlaceable;

	public PlaceableButton(GlobalReference globalReference, CursorPlaceable cursorPlaceable, SideBar sideBar) {
		super(sideBar);

		this.cursorPlaceable = cursorPlaceable;

		getComponent().setToolTipText(cursorPlaceable.getDisplayableName());

		getComponent().addActionListener(_ -> {
			globalReference.selectedForPlacement = cursorPlaceable;
		});
	}

	@Override
	public void refreshSelection() {
		if (this.globalReference.selectedForPlacement.equals(this.cursorPlaceable))
			getComponent().setBorder(getSelectedBorder(this.globalReference));
		else
			getComponent().setBorder(getDeelectedBorder(this.globalReference));
	}

	@Override
	protected void draw(Graphics g) {
		this.cursorPlaceable.draw(g, this.globalReference.getColorPalette());
	}
}

package owenparker.gui.sidebar;

import owenparker.engine.CursorPlaceable;
import owenparker.engine.colors.ColorPalette;
import owenparker.engine.colors.HasColor;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlaceableButton extends SideBarButton {

	private final CursorPlaceable cursorPlaceable;

	private Border getDeelectedBorder(GlobalReference globalReference) {
		return new LineBorder(globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES), 2);
	}

	private Border getSelectedBorder(GlobalReference globalReference) {
		return new LineBorder(globalReference.getColorPalette().getColor(ColorPalette.Slot.ACTIVE_1), 2);
	}

	public PlaceableButton(GlobalReference globalReference, CursorPlaceable cursorPlaceable, SideBar sideBar) {
		super(globalReference);

		this.cursorPlaceable = cursorPlaceable;

		refreshSelection();

		getComponent().setToolTipText(cursorPlaceable.getDisplayableName());

		getComponent().addActionListener(_ -> {
			globalReference.selectedForPlacement = cursorPlaceable;
			sideBar.refreshSelection();
		});
	}

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

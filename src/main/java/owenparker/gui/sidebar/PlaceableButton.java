package owenparker.gui.sidebar;

import owenparker.engine.CursorPlaceable;
import owenparker.engine.colors.HasColor;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.awt.*;

public class PlaceableButton extends SideBarButton {
	public PlaceableButton(GlobalReference globalReference, CursorPlaceable cursorPlaceable) {
		super(globalReference);

		setComponent(new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				cursorPlaceable.draw(g, globalReference.getColorPalette());
			}
		});

		getComponent().repaint();
	}
}

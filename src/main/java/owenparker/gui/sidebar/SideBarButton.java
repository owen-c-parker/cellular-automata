package owenparker.gui.sidebar;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class SideBarButton extends AccessorComponent<JButton> {
	public SideBarButton(SideBar sideBar) {
		super(sideBar.globalReference);
		setComponent(new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw(g);
			}
		});

		getComponent().setSize(32, 32);
		getComponent().setMaximumSize(new Dimension(32, 32));
		getComponent().setMinimumSize(new Dimension(32, 32));
		getComponent().setPreferredSize(new Dimension(32, 32));
		// JUST BE A SQUARE PLEASE
		getComponent().setAlignmentX(Component.CENTER_ALIGNMENT);
		getComponent().setPreferredSize(getComponent().getSize());

		getComponent().setBorder(getDeelectedBorder(globalReference));

		getComponent().addActionListener(_ -> sideBar.refreshSelection());

		refreshSelection();
	}

	protected Border getDeelectedBorder(GlobalReference globalReference) {
		return new LineBorder(globalReference.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES), 2);
	}

	protected Border getSelectedBorder(GlobalReference globalReference) {
		return new LineBorder(globalReference.getColorPalette().getColor(ColorPalette.Slot.ACTIVE_1), 2);
	}

	protected abstract void draw(Graphics g);

	public abstract void refreshSelection();
}

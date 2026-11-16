package owenparker.gui.sidebar;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.context.DisplayContext;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class SideBarButton extends JButton {
	public SideBarButton(SideBar sideBar) {
		super();

		setSize(32, 32);
		setMaximumSize(new Dimension(32, 32));
		setMinimumSize(new Dimension(32, 32));
		setPreferredSize(new Dimension(32, 32));
		// JUST BE A SQUARE PLEASE
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setPreferredSize(getSize());

		setBorder(getDeelectedBorder());

		addActionListener(_ -> sideBar.refreshSelection());

		refreshSelection();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	protected Border getDeelectedBorder() {
		return new LineBorder(DisplayContext.getColorPalette().getColor(ColorPalette.Slot.GRIDLINES), 2);
	}

	protected Border getSelectedBorder() {
		return new LineBorder(DisplayContext.getColorPalette().getColor(ColorPalette.Slot.ACTIVE_1), 2);
	}

	protected abstract void draw(Graphics g);

	public abstract void refreshSelection();
}

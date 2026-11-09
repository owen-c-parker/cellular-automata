package owenparker.gui.sidebar;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.awt.*;

public abstract class SideBarButton extends AccessorComponent<JButton> {
	public SideBarButton(GlobalReference globalReference) {
		super(globalReference);
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
	}

	protected abstract void draw(Graphics g);
}

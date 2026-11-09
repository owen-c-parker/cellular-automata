package owenparker.gui.sidebar;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.awt.*;

public abstract class SideBarButton extends AccessorComponent<JButton> {
	public SideBarButton(GlobalReference globalReference) {
		super(globalReference, new JButton());

		getComponent().setSize(32, 32);
		getComponent().setMaximumSize(new Dimension(32, 32));
		getComponent().setAlignmentX(Component.CENTER_ALIGNMENT);
		getComponent().setPreferredSize(getComponent().getSize());
	}
}

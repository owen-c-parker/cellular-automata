package owenparker.gui.menu_bar;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class MenuHeader extends AccessorComponent<JMenuItem> {
	public MenuHeader(GlobalReference globalReference, String labelText) {
		super(globalReference, new JMenuItem(labelText));
		getComponent().setEnabled(false);
	}
}

package owenparker.gui.menu_bar;

import owenparker.gui.context.DisplayContext;

import javax.swing.*;

public class MenuHeader extends JMenuItem {
	public MenuHeader(String labelText) {
		super(labelText);
		getComponent().setEnabled(false);
	}
}

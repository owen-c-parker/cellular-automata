package owenparker.gui.menu_bar.tools;

import owenparker.gui.context.DisplayContext;

import javax.swing.*;

public class ResetCameraMenuItem extends JMenuItem {

	public ResetCameraMenuItem() {
		super("Reset scroll to origin");

		addActionListener(_ -> {
			DisplayContext.resetCamera();
		});
	}
}

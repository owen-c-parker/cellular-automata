package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class ResetCameraMenuItem extends AccessorComponent<JMenuItem> {

	public ResetCameraMenuItem(GlobalReference globalReference) {
		super(globalReference, new JMenuItem("Reset scroll to origin"));

		getComponent().addActionListener(_ -> {
			globalReference.resetCamera();
		});
	}
}

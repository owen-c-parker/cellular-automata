package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.lang.Language;

import javax.swing.*;

public class ResetCameraMenuItem extends AccessorComponent<JMenuItem> {

	public ResetCameraMenuItem(GlobalReference globalReference) {
		super(globalReference, new JMenuItem(translation(Language.Key.RESET_CAMERA)));

		getComponent().addActionListener(_ -> {
			globalReference.resetCamera();
		});
	}
}

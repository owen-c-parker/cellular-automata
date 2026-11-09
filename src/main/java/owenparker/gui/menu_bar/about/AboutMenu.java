package owenparker.gui.menu_bar.about;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class AboutMenu extends AccessorComponent<JMenu> {
	public AboutMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu("About"));

		JMenuItem licenseInfoButton = new JMenuItem("License information");
		licenseInfoButton.addActionListener(_ -> new LicenseInfoWindow());
		getComponent().add(licenseInfoButton);
	}
}

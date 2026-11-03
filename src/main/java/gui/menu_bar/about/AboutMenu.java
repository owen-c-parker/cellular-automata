package gui.menu_bar.about;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;

public class AboutMenu extends AccessorComponent<JMenu> {
	public AboutMenu(GuiReference guiReference) {
		super(guiReference, new JMenu("About"));

		JMenuItem licenseInfoButton = new JMenuItem("License information");
		licenseInfoButton.addActionListener(_ -> new LicenseInfoWindow());
		getComponent().add(licenseInfoButton);
	}
}

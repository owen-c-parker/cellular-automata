package owenparker.gui.menu_bar.about;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.lang.Language;

import javax.swing.*;

public class AboutMenu extends AccessorComponent<JMenu> {
	public AboutMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.ABOUT_MENU_OPTION)));

		JMenuItem licenseInfoButton = new JMenuItem(translation(Language.Key.LICENSE_MENU_OPTION));
		licenseInfoButton.addActionListener(_ -> new LicenseInfoWindow());
		getComponent().add(licenseInfoButton);
	}
}

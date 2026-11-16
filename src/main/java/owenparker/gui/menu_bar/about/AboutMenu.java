package owenparker.gui.menu_bar.about;

import owenparker.gui.context.DisplayContext;

import javax.swing.*;

public class AboutMenu extends JMenu {
	public AboutMenu() {
		super("About");

		JMenuItem licenseInfoButton = new JMenuItem("License information");
		licenseInfoButton.addActionListener(_ -> new LicenseInfoWindow());
		add(licenseInfoButton);

        JMenuItem pluginsButton = new JMenuItem("Installed Plugins");
        pluginsButton.addActionListener(_ -> new InstalledPluginsWindow());
        add(pluginsButton);
	}
}

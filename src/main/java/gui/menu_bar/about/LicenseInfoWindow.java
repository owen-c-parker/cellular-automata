package gui.menu_bar.about;

import gui.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

public class LicenseInfoWindow extends GenericWindow {
	public LicenseInfoWindow() {
		setTitle("License Information");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		String license;
		try (FileReader reader = new FileReader(new File(Objects.requireNonNull(getClass().getResource("/LICENSE")).toURI()))) {
			license = reader.readAllAsString().replaceAll("\n", "<br>");
		} catch (Exception e) {
			license = String.format("Failed to find LICENSE file:\n%s\n", e);
		}

		JLabel licenseText = new JLabel(String.format("<html>%s</html>", license));

		JScrollPane scrollPane = new JScrollPane(licenseText);
		getContentPane().add(scrollPane);

		setSize(dimension.width / 2, dimension.height / 2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

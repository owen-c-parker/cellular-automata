package owenparker.gui.menu_bar.about;

import owenparker.gui.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class LicenseInfoWindow extends GenericWindow {
	public LicenseInfoWindow() {
		setTitle("License Information");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		String license;
		try (Scanner scanner = new Scanner(Objects.requireNonNull(getClass().getResourceAsStream("/LICENSE")))) {
			StringBuilder licenseStringBuilder = new StringBuilder();
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) {
				licenseStringBuilder
						.append(scanner.next())
						.append("<br>");
			}

			license = String.format("<html>%s</html>", licenseStringBuilder.toString().replaceAll("\n", "<br>"));

		} catch (Exception e) {
			license = String.format("Failed to find LICENSE file:\n%s\n", e);
		}

		JLabel licenseText = new JLabel(license);

		JScrollPane scrollPane = new JScrollPane(licenseText);
		getContentPane().add(scrollPane);

		setSize(dimension.width / 2, dimension.height / 2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

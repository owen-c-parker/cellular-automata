package owenparker.gui.menu_bar.about;

import owenparker.gui.GenericWindow;
import owenparker.gui.TextUtils;
import owenparker.lang.Language;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class LicenseInfoWindow extends GenericWindow {
	public LicenseInfoWindow() {
		setTitle(Language.currentLanguage.get(Language.Key.LICENSE_MENU_OPTION));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		String license;
		try (Scanner scanner = new Scanner(Objects.requireNonNull(getClass().getResourceAsStream("/LICENSE")))) {
			StringBuilder licenseStringBuilder = new StringBuilder();
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) {
				licenseStringBuilder
						.append(scanner.next())
						.append("\n");
			}

			license = TextUtils.convertToHTML(licenseStringBuilder.toString());

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

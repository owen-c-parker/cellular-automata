package owenparker.gui;

import owenparker.config.Configuration;
import owenparker.gui.export.ExportConfig;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.function.Consumer;

public class ConfirmationWindow<T extends Configuration> extends GenericWindow {
	public ConfirmationWindow(GlobalReference globalReference, T config, Consumer<T> confirmCallback, String confirmText) {
		setContentPane(new JPanel(new BorderLayout()));

		setTitle(confirmText);

		getContentPane().add(config.getPanel(), BorderLayout.CENTER);

		JPanel bottom = new JPanel();

		bottom.setBorder(new LineBorder(Color.BLACK, 1, false));

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(_ -> dispose());
		bottom.add(cancelButton);

		JButton confirmButton = new JButton(confirmText);
		confirmButton.addActionListener(_ -> {
			confirmCallback.accept(config);

			dispose();
		});
		bottom.add(confirmButton);

		getContentPane().add(bottom, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

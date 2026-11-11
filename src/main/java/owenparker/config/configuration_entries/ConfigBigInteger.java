package owenparker.config.configuration_entries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;

public class ConfigBigInteger extends ConfigNumber<BigInteger> {
	public ConfigBigInteger(String name, BigInteger initialValue, BigInteger min, BigInteger max) {
		super(name, initialValue, min, max);
	}

	public ConfigBigInteger(String name, BigInteger initialValue) {
		super(name, initialValue);
	}

	@Override
	public JComponent getControlComponent() {
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, textField.getPreferredSize().height));
		textField.setText(get().toString());
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				attemptSet(textField.getText());
			}
		});
		textField.addActionListener(_ -> {
			attemptSet(textField.getText());
		});
		return textField;
	}

	@Override
	public void attemptSet(Object value) {
		try {
			set(new BigInteger(value.toString()));
		} catch (NumberFormatException _) {
			set(BigInteger.ZERO);
		}
	}
}

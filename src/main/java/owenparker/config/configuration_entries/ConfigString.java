package owenparker.config.configuration_entries;

import owenparker.config.ConfigEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConfigString extends ConfigEntry<String> {

    public ConfigString(String name, String initialValue) {
        super(name, initialValue);
    }

    @Override
    public void attemptSet(Object value) {
        set(value.toString());
    }

    @Override
    public JComponent getControlComponent() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(getName()));
        JTextField textField = new JTextField(get());
		textField.setPreferredSize(new Dimension(100, textField.getPreferredSize().height));
        textField.addActionListener(_ -> set(textField.getText()));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				set(textField.getText());
			}
		});
        panel.add(textField);
        return panel;
    }
}

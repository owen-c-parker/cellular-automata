package owenparker.config;

import owenparker.engine.HasDisplayableName;

import javax.swing.*;

public class ConfigEntryString extends ConfigKeyValuePair<String> implements HasDisplayableName {
	protected ConfigEntryString(String key, String value, String displayableName) {
		super(key, value, displayableName);
	}

	@Override
	public JComponent getController() {
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel(getDisplayableName() + ": "));
		JTextField field = new JTextField(this.getValue());
		field.addActionListener(_ -> {
			setValue(field.getText());
		});
		jPanel.add(field);
		return jPanel;
	}
}

package owenparker.config.configuration_entries;

import owenparker.config.ConfigEntry;

import javax.swing.*;

public class ConfigBoolean extends ConfigEntry<Boolean> {
	public ConfigBoolean(String name, Boolean initialValue) {
		super(name, initialValue);
	}

	@Override
	protected JComponent getControlComponent() {
		JCheckBox checkBox = new JCheckBox();

		checkBox.setSelected(get());

		checkBox.addActionListener(_ -> set(checkBox.isSelected()));

		return checkBox;
	}

	@Override
	public void attemptSet(Object value) {
		set(Boolean.parseBoolean(value.toString()));
	}
}

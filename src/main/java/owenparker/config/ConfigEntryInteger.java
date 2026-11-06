package owenparker.config;

import javax.swing.*;

public class ConfigEntryInteger extends ConfigKeyValuePair<Integer> {
	protected ConfigEntryInteger(String key, Integer value, String displayableName) {
		super(key, value, displayableName);
	}

	@Override
	public JComponent getController() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(getDisplayableName() + ": "));
		JSpinner spinner = new JSpinner();
		spinner.setValue(getValue());

	}
}

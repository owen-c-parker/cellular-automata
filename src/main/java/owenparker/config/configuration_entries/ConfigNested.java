package owenparker.config.configuration_entries;

import owenparker.config.ConfigEntry;
import owenparker.config.Configuration;

import javax.swing.*;

public class ConfigNested<T extends Configuration> extends ConfigEntry<T> {

	public ConfigNested(String name, T initialValue) {
		super(name, initialValue);
	}

	@Override
	protected JComponent getControlComponent() {
		return get().getPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attemptSet(Object value) {
		if (value instanceof String string)
			get().setEntriesBasedOnConfigFileString(string);
		if (get().getClass().equals(value.getClass()))
			set((T) value);
	}
}

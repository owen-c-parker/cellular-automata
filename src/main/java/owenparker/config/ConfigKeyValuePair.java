package owenparker.config;

import owenparker.engine.HasDisplayableName;

public abstract class ConfigKeyValuePair<T> extends ConfigEntry<T> implements HasDisplayableName {
	public final String key;
	private T value;
	private final String displayableName;

	protected ConfigKeyValuePair(String key, T value, String displayableName) {
		this.key = key;
		this.value = value;
		this.displayableName = displayableName;
	}

	public T getValue() {
		return this.value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String getDisplayableName() {
		return this.displayableName;
	}
}

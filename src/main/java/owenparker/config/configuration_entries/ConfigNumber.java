package owenparker.config.configuration_entries;

import owenparker.config.ConfigEntry;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public abstract class ConfigNumber<T extends Number> extends ConfigEntry<T> {
    protected T min = null;
    protected T max = null;

    public ConfigNumber(String name, T initialValue) {
        super(name, initialValue);
    }

    public ConfigNumber(String name, T initialValue, T min, T max) {
        this(name, initialValue);
        this.max = max;
        this.min = min;
    }

    @Override
    public void set(T value) {
        if (value.doubleValue() > max.doubleValue()) {
            super.set(max);
            return;
        }

        if (value.doubleValue() < min.doubleValue()) {
            super.set(min);
            return;
        }

        super.set(value);
    }

    @Override
    public JComponent getControlComponent() {
        JSpinner spinner = new JSpinner();
        spinner.setValue(get());
        spinner.addChangeListener(_ -> attemptSet(spinner.getValue()));
        return spinner;
    }
}

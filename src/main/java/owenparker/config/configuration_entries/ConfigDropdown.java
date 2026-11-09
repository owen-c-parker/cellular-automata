package owenparker.config.configuration_entries;

import javax.swing.*;

public class ConfigDropdown<T> extends ConfigEntry<T> {
    private final T[] options;

    public ConfigDropdown(String name, T initialValue, T[] options) {
        super(name, initialValue);
        this.options = options;
    }

    public T[] getOptions() {
        return this.options;
    }

    @Override
    protected JComponent getControlComponent() {
        JComboBox<T> options = new JComboBox<>(getOptions());
        options.setSelectedItem(get());
        options.addActionListener(_ -> {
            set(options.getItemAt(options.getSelectedIndex()));
        });
        return options;
    }
}

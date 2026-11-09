package owenparker.config.configuration_entries;

import owenparker.config.ConfigEntry;

import javax.swing.*;
import java.util.*;

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

    @Override
    public void attemptSet(Object value) {
        try {
            List<T> things = new ArrayList<>(List.of(options));
            set(things.stream().filter(t -> t.toString().equals(value.toString())).findFirst().orElse(options[0]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

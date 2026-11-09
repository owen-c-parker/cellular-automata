package owenparker.config.configuration_entries;

import javax.swing.*;

public class ConfigInteger extends ConfigEntry<Integer> {
    public ConfigInteger(String name, Integer initialValue) {
        super(name, initialValue);
    }

    @Override
    public JComponent getControlComponent() {
        JSpinner spinner = new JSpinner();
        spinner.setValue(get());
        spinner.addChangeListener(_ -> set((Integer) spinner.getValue()));
        return spinner;
    }
}

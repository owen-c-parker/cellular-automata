package owenparker.config.configuration_entries;

import javax.swing.*;

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
        textField.addActionListener(_ -> set(textField.getText()));
        panel.add(textField);
        return panel;
    }
}

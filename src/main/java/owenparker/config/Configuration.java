package owenparker.config;

import owenparker.config.configuration_entries.ConfigEntry;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public abstract class Configuration {
    public final List<? extends ConfigEntry<?>> getEntries() {
        // This method is DISGUSTING, I am so sorry. I try to avoid reflection if I can
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> ConfigEntry.class.isAssignableFrom(field.getType()))
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return (ConfigEntry<?>) field.get(this);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        for (ConfigEntry<?> entry : getEntries()) {
            panel.add(entry.getControlPanel());
        }
        return panel;
    }

    public String convertToText() {
        StringBuilder configString = new StringBuilder();
        getEntries().forEach(configEntry -> configString.append(configEntry.getConfigLine()));
        return configString.toString();
    }
}

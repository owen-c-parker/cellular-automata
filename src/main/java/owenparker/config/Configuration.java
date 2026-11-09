package owenparker.config;

import owenparker.config.configuration_entries.ConfigEntry;
import owenparker.gui.FileManagement;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public abstract class Configuration implements Serializable {
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

    public final void setEntriesBasedOnConfigFile(File file) {
        try (FileReader reader = new FileReader(file)) {
            setEntriesBasedOnConfigFile(reader.readAllAsString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final void setEntriesBasedOnConfigFile(String config) {
        String[] lines = config.split("\n");

        List<? extends ConfigEntry<?>> entries = getEntries();

        for (String line : lines) {
            String key = line.substring(0, line.indexOf("="));
            String value = line.substring(line.indexOf("=") + 1);

            for (ConfigEntry<?> configEntry : entries) {
                if (configEntry.getName().equals(key)) {
                    configEntry.attemptSet(value);
                }
            }
        }
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

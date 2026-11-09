package owenparker.config;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
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
            setEntriesBasedOnConfigFileString(reader.readAllAsString());
        } catch (IOException e) {
            setEntriesBasedOnConfigFileString("");
        }
    }

    public final void setEntriesBasedOnConfigFileString(String config) {
        String[] lines = config.split("\n");

        List<? extends ConfigEntry<?>> entries = getEntries();

        for (String line : lines) {
            if (line.isEmpty())
                continue;

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
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        for (ConfigEntry<?> entry : getEntries()) {
            subPanel.add(entry.getControlPanel());
            subPanel.add(new JSeparator());
        }
        subPanel.remove(subPanel.getComponentCount() - 1);
        JScrollPane pane = new JScrollPane(subPanel);
        JPanel panel = new JPanel();
        panel.add(pane);
        return panel;
    }

    public String convertToText() {
        StringBuilder configString = new StringBuilder();
        getEntries().forEach(configEntry -> configString.append(configEntry.getConfigLine()));
        return configString.toString();
    }
}

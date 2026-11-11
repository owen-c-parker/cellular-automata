package owenparker.config;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
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
		List<String> entryStrings = getEntryStrings(config);

        List<? extends ConfigEntry<?>> entries = getEntries();

        for (String entryString : entryStrings) {
            if (entryString.isEmpty())
                continue;

            String key = entryString.substring(0, entryString.indexOf("="));
            String value = entryString.substring(entryString.indexOf("=") + 2, entryString.length() - 1);

            for (ConfigEntry<?> configEntry : entries) {
                if (configEntry.getName().equals(key)) {
                    configEntry.attemptSet(value);
                }
            }
        }
    }

	private List<String> getEntryStrings(String config) {
		LinkedList<String> strings = new LinkedList<>();

		int index = -1;
		int bracketLevel = 0;
		StringBuilder workingBuilder = new StringBuilder();
		while (index < config.length() - 1) {
			index++;

			char character = config.charAt(index);

			switch (character) {
				case ';':
					if (bracketLevel <= 0) {
						strings.add(workingBuilder.toString());
						workingBuilder = new StringBuilder();
						continue;
					}
					break;
				case '{':
					bracketLevel++;
					break;
				case '}':
					bracketLevel--;
					break;
				case '\n':
					continue;
			}

			workingBuilder.append(config.charAt(index));
		}

		return strings;
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

	@Override
	public String toString() {
		return convertToText();
	}
}

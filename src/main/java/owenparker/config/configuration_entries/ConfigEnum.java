package owenparker.config.configuration_entries;

import javax.swing.*;

public class ConfigEnum<T extends Enum<T>> extends ConfigDropdown<T> {
    public ConfigEnum(String name, T initialValue) {
        super(name, initialValue, initialValue.getDeclaringClass().getEnumConstants());
    }
}

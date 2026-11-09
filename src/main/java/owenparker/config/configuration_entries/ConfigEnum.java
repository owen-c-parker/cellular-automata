package owenparker.config.configuration_entries;

public class ConfigEnum<T extends Enum<T>> extends ConfigDropdown<T> {
    public ConfigEnum(String name, T initialValue) {
        super(name, initialValue, initialValue.getDeclaringClass().getEnumConstants());
    }
}

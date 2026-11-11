package owenparker.config.configuration_entries;

public class ConfigInteger extends ConfigNumber<Integer> {
    public ConfigInteger(String name, Integer initialValue) {
        super(name, initialValue);
    }

    public ConfigInteger(String name, Integer initialValue, Integer min, Integer max) {
        super(name, initialValue, min, max);
    }

    @Override
    public void attemptSet(Object value) {
        set(Integer.parseInt(value.toString()));
    }
}

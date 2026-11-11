package owenparker.config.configuration_entries;

public class ConfigDouble extends ConfigNumber<Double> {
    public ConfigDouble(String name, Double initialValue) {
        super(name, initialValue);
    }

    public ConfigDouble(String name, Double initialValue, Double min, Double max) {
        super(name, initialValue, min, max);
    }

    @Override
    public void attemptSet(Object value) {
        set(Double.parseDouble(value.toString()));
    }
}

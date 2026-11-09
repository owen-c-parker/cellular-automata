package owenparker.config.configuration_entries;

import javax.swing.*;

public class ConfigInteger extends ConfigNumber<Integer> {
    public ConfigInteger(String name, Integer initialValue) {
        super(name, initialValue);
    }

    public ConfigInteger(String name, Integer initialValue, Integer min, Integer max) {
        super(name, initialValue, min, max);
    }

    @Override
    public void attemptSet(Object value) {
        if (value instanceof Integer integer) {
            set(integer);
            return;
        }
        set(Integer.getInteger(value.toString()));
    }
}

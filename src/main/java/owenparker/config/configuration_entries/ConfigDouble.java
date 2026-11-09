package owenparker.config.configuration_entries;

import javax.swing.*;

public class ConfigDouble extends ConfigNumber<Double> {
    public ConfigDouble(String name, Double initialValue) {
        super(name, initialValue);
    }

    public ConfigDouble(String name, Double initialValue, Double min, Double max) {
        super(name, initialValue, min, max);
    }

    @Override
    public void attemptSet(Object value) {
		if (value == null)
			return;
        if (value instanceof Double number)
            set(number);
        set(Double.valueOf(value.toString()));
    }
}

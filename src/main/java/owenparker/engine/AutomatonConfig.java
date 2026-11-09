package owenparker.engine;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigEnum;
import owenparker.config.configuration_entries.ConfigInteger;

public class AutomatonConfig extends Configuration {
    public final ConfigInteger width = new ConfigInteger("Width", 50, 1, Integer.MAX_VALUE);
    public final ConfigInteger height = new ConfigInteger("Height", 50, 1, Integer.MAX_VALUE);
    public final ConfigEnum<BorderPolicy> borderPolicy = new ConfigEnum<>("Border policy", BorderPolicy.NO_BORDER);

    public OrderedPair getSize() {
        return new OrderedPair(width.get(), height.get());
    }

    public BorderPolicy getBorderPolicy() {
        return borderPolicy.get();
    }
}

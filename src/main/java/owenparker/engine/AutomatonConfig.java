package owenparker.engine;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigEntry;
import owenparker.config.configuration_entries.ConfigEnum;
import owenparker.config.configuration_entries.ConfigInteger;

import java.util.List;

public class AutomatonConfig extends Configuration {
    public final ConfigInteger width = new ConfigInteger("Width", 50);
    public final ConfigInteger height = new ConfigInteger("Height", 50);
    public final ConfigEnum<BorderPolicy> borderPolicy = new ConfigEnum<>("Border policy: ", BorderPolicy.NO_BORDER);

    public OrderedPair getSize() {
        return new OrderedPair(width.get(), height.get());
    }

    public BorderPolicy getBorderPolicy() {
        return borderPolicy.get();
    }
}

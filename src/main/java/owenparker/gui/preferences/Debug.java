package owenparker.gui.preferences;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigBoolean;

public class Debug extends Configuration {
	public final ConfigBoolean drawUpdater = new ConfigBoolean("Show which cells are to be updated", false);
}

package owenparker.engine;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigEnum;
import owenparker.config.configuration_entries.ConfigInteger;

/**
 * Configuration for an actively running automaton
 */
public class AutomatonConfig extends Configuration {
	/**
	 * Width of the {@link Board}.
	 * @see #getSize
	 */
	public final ConfigInteger width = new ConfigInteger("Width", 50, 1, Integer.MAX_VALUE);

	/**
	 * Height of the {@link Board}.
	 * @see #getSize
	 */
	public final ConfigInteger height = new ConfigInteger("Height", 50, 1, Integer.MAX_VALUE);


	/**
	 * How the simulation should treat the borders
	 */
    public final ConfigEnum<BorderPolicy> borderPolicy = new ConfigEnum<>("Border policy", BorderPolicy.NO_BORDER);

	/**
	 * The size of the simulation. Unused if {@link #borderPolicy} is {@link BorderPolicy#NO_BORDER}.
	 * @return size of simulation
	 */
    public OrderedPair getSize() {
        return new OrderedPair(this.width.get(), this.height.get());
    }

	/**
	 *
	 * @return current border policy
	 * @see #borderPolicy
	 */
    public BorderPolicy getBorderPolicy() {
        return this.borderPolicy.get();
    }
}

package owenparker.engine;

import owenparker.config.Configuration;
import owenparker.engine.exceptions.InvalidConfigException;

import java.io.Serializable;

/**
 * Automaton factories are displayed in the "new" menu under "file".
 */
public abstract class AutomatonFactory <T extends Configuration> implements HasDisplayableName, Serializable {
	/**
	 * Create an {@link owenparker.engine.Automaton} based on {@link T}.
	 * @param config configuration for the automaton
	 * @return the created automaton
	 * @throws InvalidConfigException if the state of the configuration is invalid
	 */
	public abstract Automaton buildAutomaton(T config) throws InvalidConfigException;

	/**
	 * Returns an instance of {@link T} for the user to fill out and later pass to {@link #buildAutomaton}.
	 * @return instance of {@link T}
	 */
	public abstract T getConfig();
}

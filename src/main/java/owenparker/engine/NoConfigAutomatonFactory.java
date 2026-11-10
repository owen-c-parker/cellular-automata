package owenparker.engine;

import owenparker.config.Configuration;

public abstract class NoConfigAutomatonFactory extends AutomatonFactory<Configuration> {
	@Override
	public final Automaton buildAutomaton(Configuration config) {
		return buildAutomaton();
	}

	public abstract Automaton buildAutomaton();

	@Override
	public Configuration getConfig() {
		return null;
	}
}

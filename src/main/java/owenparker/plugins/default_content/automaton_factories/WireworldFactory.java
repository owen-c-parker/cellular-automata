package owenparker.plugins.default_content.automaton_factories;

import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;
import owenparker.plugins.default_content.automata.Wireworld;

public class WireworldFactory extends NoConfigAutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new Wireworld();
	}

	@Override
	public String getDisplayableName() {
		return "Wireworld";
	}
}

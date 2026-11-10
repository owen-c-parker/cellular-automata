package owenparker.automaton_factories;

import owenparker.automata.Wireworld;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.NoConfigAutomatonFactory;

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

package owenparker.automaton_factories;

import owenparker.automata.Wireworld;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

public class WireworldFactory extends AutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new Wireworld();
	}

	@Override
	public String getDisplayableName() {
		return "Wireworld";
	}
}

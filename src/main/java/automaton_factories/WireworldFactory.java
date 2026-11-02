package automaton_factories;

import automata.Wireworld;
import engine.Automaton;
import engine.AutomatonFactory;

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

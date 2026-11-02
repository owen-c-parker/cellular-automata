package automaton_factories;

import automata.Rule110;
import engine.Automaton;
import engine.AutomatonFactory;

public class Rule110Factory extends AutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new Rule110();
	}

	@Override
	public String getDisplayableName() {
		return "Rule 110";
	}
}

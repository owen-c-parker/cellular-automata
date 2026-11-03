package owenparker.automaton_factories;

import owenparker.automata.Rule110;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

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

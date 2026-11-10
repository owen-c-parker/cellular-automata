package owenparker.automaton_factories;

import owenparker.automata.Rule110;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.NoConfigAutomatonFactory;

public class Rule110Factory extends NoConfigAutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new Rule110();
	}

	@Override
	public String getDisplayableName() {
		return "Rule 110";
	}
}

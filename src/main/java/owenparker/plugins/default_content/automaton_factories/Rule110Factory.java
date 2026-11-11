package owenparker.plugins.default_content.automaton_factories;

import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;
import owenparker.plugins.default_content.automata.Rule110;

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

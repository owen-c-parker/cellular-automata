package owenparker.plugins.default_content.automaton_factories;

import owenparker.plugins.default_content.automata.BriansBrain;
import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;

public class BriansBrainFactory extends NoConfigAutomatonFactory {

	@Override
	public String getDisplayableName() {
		return "Brian's Brain";
	}

	@Override
	public Automaton buildAutomaton() {
		return new BriansBrain();
	}
}

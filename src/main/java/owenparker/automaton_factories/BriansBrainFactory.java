package owenparker.automaton_factories;

import owenparker.automata.BriansBrain;
import owenparker.config.Configuration;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
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

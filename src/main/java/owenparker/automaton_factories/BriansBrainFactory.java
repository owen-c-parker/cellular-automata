package owenparker.automaton_factories;

import owenparker.automata.BriansBrain;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

public class BriansBrainFactory extends AutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new BriansBrain();
	}

	@Override
	public String getDisplayableName() {
		return "Brian's Brain";
	}
}

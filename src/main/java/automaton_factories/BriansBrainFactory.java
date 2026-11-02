package automaton_factories;

import automata.BriansBrain;
import engine.Automaton;
import engine.AutomatonFactory;

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

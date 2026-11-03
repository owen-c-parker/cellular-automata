package owenparker.automaton_factories;

import owenparker.automata.LangtonsAnt;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

public class LangtonsAntFactory extends AutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new LangtonsAnt();
	}

	@Override
	public String getDisplayableName() {
		return "Langton's Ant";
	}
}

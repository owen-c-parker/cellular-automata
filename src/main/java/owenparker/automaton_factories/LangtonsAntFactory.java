package owenparker.automaton_factories;

import owenparker.automata.LangtonsAnt;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.NoConfigAutomatonFactory;

public class LangtonsAntFactory extends NoConfigAutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new LangtonsAnt();
	}

	@Override
	public String getDisplayableName() {
		return "Langton's Ant";
	}
}

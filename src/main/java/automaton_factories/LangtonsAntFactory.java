package automaton_factories;

import automata.LangtonsAnt;
import engine.Automaton;
import engine.AutomatonFactory;

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

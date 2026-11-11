package owenparker.plugins.default_content.automaton_factories;

import owenparker.plugins.default_content.automata.LangtonsAnt;
import owenparker.engine.Automaton;
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

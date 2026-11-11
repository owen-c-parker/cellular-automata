package owenparker.plugins.default_content.automaton_factories;

import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;
import owenparker.plugins.default_content.automata.KnightCGOL;

public class KnightCGOLFactory extends NoConfigAutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new KnightCGOL();
	}

	@Override
	public String getDisplayableName() {
		return "Knight's Game of Life";
	}
}

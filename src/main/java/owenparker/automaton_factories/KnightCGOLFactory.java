package owenparker.automaton_factories;

import owenparker.automata.KnightCGOL;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.NoConfigAutomatonFactory;

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

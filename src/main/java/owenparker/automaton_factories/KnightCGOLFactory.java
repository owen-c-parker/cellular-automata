package owenparker.automaton_factories;

import owenparker.automata.KnightCGOL;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

public class KnightCGOLFactory extends AutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new KnightCGOL();
	}

	@Override
	public String getDisplayableName() {
		return "Knight's Game of Life";
	}
}

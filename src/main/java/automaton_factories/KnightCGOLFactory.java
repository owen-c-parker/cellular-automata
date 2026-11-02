package automaton_factories;

import automata.KnightCGOL;
import engine.Automaton;
import engine.AutomatonFactory;

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

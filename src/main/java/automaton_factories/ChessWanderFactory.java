package automaton_factories;

import automata.ChessWander;
import engine.Automaton;
import engine.AutomatonFactory;

public class ChessWanderFactory extends AutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new ChessWander();
	}

	@Override
	public String getDisplayableName() {
		return "Chess Wander";
	}
}

package owenparker.automaton_factories;

import owenparker.automata.ChessWander;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

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

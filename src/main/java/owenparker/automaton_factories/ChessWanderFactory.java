package owenparker.automaton_factories;

import owenparker.automata.ChessWander;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.NoConfigAutomatonFactory;

public class ChessWanderFactory extends NoConfigAutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new ChessWander();
	}

	@Override
	public String getDisplayableName() {
		return "Chess Wander";
	}
}

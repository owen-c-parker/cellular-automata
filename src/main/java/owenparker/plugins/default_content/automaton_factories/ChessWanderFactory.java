package owenparker.plugins.default_content.automaton_factories;

import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;
import owenparker.plugins.default_content.automata.ChessWander;

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

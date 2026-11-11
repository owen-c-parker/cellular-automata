package owenparker.plugins.default_content.automaton_factories;

import owenparker.plugins.default_content.automata.ChessWander;
import owenparker.engine.Automaton;
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

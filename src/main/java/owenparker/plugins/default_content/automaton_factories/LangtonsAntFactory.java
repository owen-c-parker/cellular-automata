package owenparker.plugins.default_content.automaton_factories;

import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;
import owenparker.plugins.default_content.automata.turmites.TurmiteAutomaton;
import owenparker.plugins.default_content.automata.turmites.TurnDecision;

public class LangtonsAntFactory extends NoConfigAutomatonFactory {
	@Override
	public Automaton buildAutomaton() {
		return new TurmiteAutomaton() {
			@Override
			public String getNameForAgent() {
				return "Ant";
			}

			@Override
			public TurnDecision[] getTurnDecisions() {
				return new TurnDecision[] {
						TurnDecision.RIGHT,
						TurnDecision.LEFT
				};
			}
		};
	}

	@Override
	public String getDisplayableName() {
		return "Langton's Ant";
	}
}

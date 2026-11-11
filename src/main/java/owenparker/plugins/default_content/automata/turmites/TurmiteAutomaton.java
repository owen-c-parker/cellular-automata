package owenparker.plugins.default_content.automata.turmites;

import owenparker.engine.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TurmiteAutomaton extends Automaton {

	public abstract String getNameForAgent();
	public String getPluralNameForAgent() {
		return getNameForAgent() + "s";
	}

	public abstract TurnDecision[] getTurnDecisions();

	private List<TurmiteState> indexedStates;

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.EMPTY;
	}

	@Override
	protected State generateDefaultState() {
		return lazyGetIndexedStates().getFirst();
	}

	@Override
	public List<State> getSelectableCellStates() {
		return new LinkedList<>(this.indexedStates);
	}

	private TurmiteAgentFactory turmiteAgentFactory;

	@Override
	public List<AgentFactory> getSelectableAgentFactories() {
		if (this.turmiteAgentFactory == null)
			this.turmiteAgentFactory = new TurmiteAgentFactory(getNameForAgent(), getTurnDecisions(), lazyGetIndexedStates());

		return List.of(this.turmiteAgentFactory);
	}

	private List<TurmiteState> lazyGetIndexedStates() {
		if (this.indexedStates == null) {
			this.indexedStates = new LinkedList<>();
			for (int index = 0; index < getTurnDecisions().length; index++) {
				this.indexedStates.add(new TurmiteState(index));
			}
		}
		return this.indexedStates;
	}

	@Override
	public String getDisplayableName() {
		return String.format("%s - %s", getPluralNameForAgent(), getTurnDecisionsAsString());
	}

	private String getTurnDecisionsAsString() {
		Map<TurnDecision, String> map = Map.of(
				TurnDecision.RIGHT, "R",
				TurnDecision.LEFT, "L",
				TurnDecision.NONE, "N",
				TurnDecision.REVERSE, "U"
		);
		return Arrays.stream(getTurnDecisions()).map(map::get).collect(Collectors.joining());
	}

	@Override
	public CursorPlaceable getSelectedByDefault() {
		return getSelectableAgentFactories().getFirst();
	}
}

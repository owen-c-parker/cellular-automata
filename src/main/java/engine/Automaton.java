package engine;

import java.util.*;

public abstract class Automaton {
	private Board board = new Board(getDefaultState());
	private final Neighborhood wakeUpNeighborhood;

	Set<Agent> agents = new HashSet<>();

	public final boolean addAgent(Agent agent) {
		return this.agents.add(agent);
	}

	public final boolean removeAgent(Agent agent) {
		return this.agents.remove(agent);
	}

	protected Automaton(Neighborhood wakeUpNeighborhood) {
		this.wakeUpNeighborhood = wakeUpNeighborhood;
	}

	public abstract String getDisplayableName();

	private void updateAllListeners() {
		this.updateListeners.forEach(Runnable::run);
	}

	public final void step() {
		this.board = this.board.getUpdatedBoard(getWakeUpNeighborhood());
		stepAllAgents();
		updateAllListeners();
	}

	private void stepAllAgents() {
		this.agents.forEach(agent -> agent.step(this.board));
	}

	/**
	 * Used by the lazy evaluation algorithm
	 * @return Neighborhood to wake default cells around non-empty cells
	 */
	protected Neighborhood getWakeUpNeighborhood() {
		return this.wakeUpNeighborhood;
	}

	public final void setCellAt(OrderedPair location, State state) {
		this.board.setCellAt(location, state);
		updateAllListeners();
	}

	List<Runnable> updateListeners = new LinkedList<>();

	public final void registerUpdateListener(Runnable runnable) {
		this.updateListeners.add(runnable);
	}

	public abstract List<State> getSelectableCellStates();
	public List<AgentBehavior> getSelectableAgentBehaviors() {
		return new ArrayList<>();
	}

	public abstract State getDefaultState();

	public final State getCellAt(OrderedPair orderedPair) {
		return this.board.getCellAt(orderedPair);
	}

	@Override
	public final String toString() {
		return getDisplayableName();
	}

	public final int getActiveCellCount() {
		return this.board.cells.size();
	}

	public final void clear() {
		this.board = new Board(getDefaultState());
		updateAllListeners();
	}
}

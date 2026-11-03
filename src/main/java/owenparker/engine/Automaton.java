package owenparker.engine;

import java.io.Serializable;
import java.util.*;

public abstract class Automaton implements HasDisplayableName, Serializable {
	private Board board = new Board(getDefaultState());

	Set<Agent> agents = new HashSet<>();

    public CursorPlaceable getSelectedByDefault() {
        List<State> states = getSelectableCellStates();
        List<AgentFactory> agentFactories = getSelectableAgentFactories();
        if (states.size() > 1)
            return states.get(1);
        if (!states.isEmpty())
            return states.getFirst();
        if (!agentFactories.isEmpty())
            return agentFactories.getFirst();
        return null;
    }

	public final boolean addAgent(Agent agent) {
		return this.agents.add(agent);
	}

	public final boolean removeAgent(Agent agent) {
		return this.agents.remove(agent);
	}

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
	protected abstract Neighborhood getWakeUpNeighborhood();

	public final void setCellAt(OrderedPair location, State state) {
		this.board.setCellAt(location, state);
		updateAllListeners();
	}

	List<Runnable> updateListeners = new LinkedList<>();

	public final void registerUpdateListener(Runnable runnable) {
		this.updateListeners.add(runnable);
	}

	public List<State> getSelectableCellStates() {return List.of();};
	public List<AgentFactory> getSelectableAgentFactories() {
		return List.of();
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
		this.agents = new HashSet<>();
		updateAllListeners();
	}

    public Collection<Agent> getAgents() {
        return Collections.unmodifiableCollection(this.agents);
    }
}

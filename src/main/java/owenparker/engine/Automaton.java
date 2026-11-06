package owenparker.engine;

import javax.swing.border.Border;
import java.io.Serializable;
import java.util.*;

import static owenparker.engine.BorderPolicy.*;

public abstract class Automaton implements HasDisplayableName, Serializable {
	private Board board = new Board(getDefaultState(), NO_BORDER, Board.DEFAULT_EXTENT);

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

	public final void addAgent(Agent agent) {
		this.agents.add(agent);
	}

	public final void removeAgent(Agent agent) {
		this.agents.remove(agent);
	}

	public final void step() {
		this.board = this.board.getUpdatedBoard(getWakeUpNeighborhood());
		stepAllAgents();
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
	}

	public final State getCellAt(OrderedPair position) {
		if (position == null)
			return getDefaultState();
		return this.board.getCellAt(position);
	}

	public List<State> getSelectableCellStates() {return List.of();};

	public List<AgentFactory> getSelectableAgentFactories() {
		return List.of();
	}

	public abstract State getDefaultState();

	public BorderPolicy getBorderPolicy() {
		return this.board.borderPolicy;
	}

	public void setBorderPolicy(BorderPolicy borderPolicy) {
		this.board.borderPolicy = borderPolicy;
	}

	@Override
	public final String toString() {
		return getDisplayableName();
	}

	public final int getActiveCellCount() {
		return this.board.cells.size();
	}

	public final void clear() {
		this.board = this.board.makeEmptyBoardWithSameProperties();
		this.agents = new HashSet<>();
	}

    public Collection<Agent> getAgents() {
        return Collections.unmodifiableCollection(this.agents);
    }

	public void deleteAgentAt(OrderedPair orderedPair) {
		OrderedPair transformedLocation = this.board.transformLocation(orderedPair);
		if (transformedLocation == null)
			 return;
		for (Agent agent : getAgents()) {
			OrderedPair agentLocation = this.board.transformLocation(agent.getPosition());
			if (transformedLocation.equals(agentLocation)) {
				removeAgent(agent);
				return;
			}
		}
	}

	public Collection<Agent> findAgentsAt(OrderedPair location) {
		List<Agent> agents = new LinkedList<>();
		location = this.board.transformLocation(location);
		if (location == null)
			return agents;
		for (Agent agent : getAgents()) {
			OrderedPair agentPosition = this.board.transformLocation(agent.getPosition());
			if (location.equals(agentPosition))
				agents.add(agent);
		}
		return agents;
	}
}

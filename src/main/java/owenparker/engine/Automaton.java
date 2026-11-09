package owenparker.engine;

import java.io.Serializable;
import java.sql.Array;
import java.util.*;

public abstract class Automaton implements HasDisplayableName, Serializable {
	private Board board;
    private final AutomatonConfig config = new AutomatonConfig();
    private int totalSteps = 0;

    protected Automaton() {
        board = new Board(getDefaultState(), getWakeUpNeighborhood(), config);
    }

    public AutomatonConfig getConfig() {
        return config;
    }

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
        this.totalSteps++;
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
		return this.config.getBorderPolicy();
	}

	public void setBorderPolicy(BorderPolicy borderPolicy) {
		this.config.borderPolicy.set(borderPolicy);
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

    public int getNumberOfCellsThatMightMove() {
        return this.board.getNumberOfCellsThatMightMove();
    }

    public Collection<OrderedPair> getAllCellLocations() {
        return this.board.cells.keySet();
    }

    public OrderedPair[] getExtent() {
        List<OrderedPair> cellLocations = new ArrayList<>(getAllCellLocations());
        int left = cellLocations.getFirst().x();
        int right = cellLocations.getFirst().x();
        int top = cellLocations.getFirst().y();
        int bottom = cellLocations.getFirst().y();

        for (OrderedPair location : cellLocations) {
            int x = location.x();
            int y = location.y();
            if (x < left)
                left = x;
            if (x > right)
                right = x;
            if (y < top)
                top = y;
            if (y > bottom)
                bottom = y;
        }

        return new OrderedPair[]{
                new OrderedPair(left, top),
                new OrderedPair(right, bottom)
        };
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }
}

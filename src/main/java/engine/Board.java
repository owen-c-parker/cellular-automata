package engine;

import java.util.*;

import static engine.OrderedPair.*;

public class Board {
	private static final int THREAD_COUNT = 32;
	HashMap<OrderedPair, State> cells = new HashMap<>();
	private final State defaultState;

	public Board(State defaultState) {
		this.defaultState = defaultState;
	}

	public State getCellAt(OrderedPair location) {
		return this.cells.getOrDefault(location, this.defaultState);
	}

	public void setCellAt(OrderedPair location, State state) {
		if (state.equals(getDefaultState()) && getCellAt(location).equals(getDefaultState())) return;
		this.cells.put(location, state);
	}

	public Board getUpdatedBoard(Neighborhood wakeUpNeighborhood) {
		Board updatedBoard = new Board(this.defaultState);

		Set<OrderedPair> knownCellLocations = this.cells.keySet();
		Set<OrderedPair> locationsToUpdate = new HashSet<>();

		for (OrderedPair orderedPair : knownCellLocations) {
			locationsToUpdate.add(orderedPair);
			locationsToUpdate.addAll(
					wakeUpNeighborhood
							.getAll()
							.stream()
							.map(orderedPair::add)
							.toList()
			);
		}

		for (OrderedPair locationToUpdate : locationsToUpdate) {
			State state = getCellAt(locationToUpdate);
			updatedBoard.setCellAt(locationToUpdate, state.getNewState(this, locationToUpdate));
		}

		return updatedBoard;
	}

	public static final Neighborhood MOORE_NEIGHBORHOOD = new Neighborhood(
			NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
	);

	public static final Neighborhood VON_NEUMANN_NEIGHBORHOOD = new Neighborhood(
			NORTH, SOUTH, EAST, WEST
	);

	public static final Neighborhood KNIGHT_NEIGHBORHOOD = new Neighborhood(
			NORTH.add(NORTH_EAST), NORTH.add(NORTH_WEST),
			EAST.add(NORTH_EAST) , EAST.add(SOUTH_EAST),
			WEST.add(NORTH_WEST) , WEST.add(SOUTH_WEST),
			SOUTH.add(SOUTH_EAST), SOUTH.add(SOUTH_WEST)
	);

	public Collection<State> getNeighborsOf(Neighborhood neighborhood, OrderedPair location) {
		return neighborhood
				.getAll()
				.stream()
				.map(location::add)
				.map(this::getCellAt)
				.toList();
	}

	public State getDefaultState() {
		return this.defaultState;
	}
}

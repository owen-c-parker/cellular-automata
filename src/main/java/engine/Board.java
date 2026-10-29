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

	public Board getUpdatedBoard(Collection<OrderedPair> wakeUpNeighborhood) {
		Board updatedBoard = new Board(this.defaultState);

		Set<OrderedPair> knownCellLocations = this.cells.keySet();
		Set<OrderedPair> locationsToUpdate = new HashSet<>();

		for (OrderedPair orderedPair : knownCellLocations) {
			locationsToUpdate.add(orderedPair);
			locationsToUpdate.addAll(
					wakeUpNeighborhood
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

	public static final Collection<OrderedPair> MOORE_NEIGHBORHOOD = List.of(
			NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
	);

	public static final Collection<OrderedPair> VON_NEUMANN_NEIGHBORHOOD = List.of(
			NORTH, SOUTH, EAST, WEST
	);

	public Collection<State> getNeighborsOf(Collection<OrderedPair> neighborhood, OrderedPair location) {
		return neighborhood
				.stream()
				.map(location::add)
				.map(this::getCellAt)
				.toList();
	}

	public Collection<State> getMooreNeighborsOf(OrderedPair location) {
		return getNeighborsOf(MOORE_NEIGHBORHOOD, location);
	}

	public Collection<State> getVonNeumannNeighborsOf(OrderedPair location) {
		return getNeighborsOf(VON_NEUMANN_NEIGHBORHOOD, location);
	}

	public State getDefaultState() {
		return this.defaultState;
	}
}

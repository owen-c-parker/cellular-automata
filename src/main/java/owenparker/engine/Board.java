package owenparker.engine;

import java.io.Serializable;
import java.util.*;

import static owenparker.engine.BorderPolicy.FILL_WITH_DEFAULT;
import static owenparker.engine.BorderPolicy.LOOP;

public final class Board implements Serializable {
	Map<OrderedPair, State> cells = new HashMap<>();
	private final State defaultState;

	public BorderPolicy borderPolicy = LOOP;

	public OrderedPair extent = new OrderedPair(50, 50);

	OrderedPair transformLocation(OrderedPair location) {
		if (this.borderPolicy.equals(LOOP))
			return location.mod(this.extent);
		if (this.borderPolicy.equals(FILL_WITH_DEFAULT) && !isInsideExtent(location))
			return null;
		return location;
	}

	private boolean isInsideExtent(OrderedPair position) {
		return
				position.x() >= 0 &&
				position.y() >= 0 &&
				position.x() < this.extent.x() &&
				position.y() < this.extent.y()
		;
	}

	public Board(State defaultState) {
		this.defaultState = defaultState;
	}


	public State getCellAt(OrderedPair location) {
		location = transformLocation(location);
		if (location == null)
			return this.defaultState;
		return this.cells.getOrDefault(location, this.defaultState);
	}

	public void setCellAt(OrderedPair location, State state) {
		location = transformLocation(location);
		if (location == null || state.equals(getDefaultState()) && getCellAt(location).equals(getDefaultState())) return;
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
							.map(this::transformLocation)
							.toList()
			);
		}

		for (OrderedPair locationToUpdate : locationsToUpdate) {
			State state = getCellAt(locationToUpdate);
			updatedBoard.setCellAt(locationToUpdate, state.getNewState(this, locationToUpdate));
		}

		return updatedBoard;
	}

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

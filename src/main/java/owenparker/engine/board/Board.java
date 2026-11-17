package owenparker.engine.board;

import owenparker.engine.*;
import owenparker.gui.Launcher;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

import static owenparker.engine.BorderPolicy.FILL_WITH_DEFAULT;
import static owenparker.engine.BorderPolicy.LOOP;

/**
 * Uses {@link #cells} to store the current state of the board.
 */
public final class Board implements Serializable {
    private final AutomatonConfig config;
    public Map<OrderedPair, State> cells = new HashMap<>();
    Set<OrderedPair> cellsThatMightMove = new HashSet<>();
	private final State defaultState;
    private final Neighborhood wakeUpNeighborhood;

    private OrderedPair getExtent() {
        return this.config.getSize();
    }

    private BorderPolicy getBorderPolicy() {
        return this.config.getBorderPolicy();
    }

	public OrderedPair transformLocation(OrderedPair location) {
		if (getBorderPolicy().equals(LOOP))
			return location.mod(this.getExtent());
		if (getBorderPolicy().equals(FILL_WITH_DEFAULT) && !isInsideExtent(location))
			return null;
		return location;
	}

	private boolean isInsideExtent(OrderedPair position) {
		return
                position == null ||
				position.x().compareTo(BigInteger.ZERO) >= 0 &&
				position.y().compareTo(BigInteger.ZERO) >= 0 &&
				position.x().compareTo(this.getExtent().x()) < 0 &&
				position.y().compareTo(this.getExtent().y()) < 0
		;
	}

	/**
	 * @param defaultState usually an empty state
	 * @param wakeUpNeighborhood neighborhood used to notify cells
	 * @param config configuration of the parent {@link Automaton}
	 */
	public Board(State defaultState, Neighborhood wakeUpNeighborhood, AutomatonConfig config) {
		this.defaultState = defaultState;
		this.config = config;
        this.wakeUpNeighborhood = wakeUpNeighborhood;
	}

	public Board makeEmptyBoardWithSameProperties() {
		return new Board(getDefaultState(), this.wakeUpNeighborhood, this.config);
	}

	/**
	 * @param location location of cell to get
	 * @return state of cell
	 */
	public State getCellAt(OrderedPair location) {
		location = transformLocation(location);
		if (location == null)
			return this.defaultState;
		return this.cells.getOrDefault(location, this.defaultState);
	}

	/**
	 * @param location location of cell to set
	 * @param state state of cell
	 */
	public void setCellAt(OrderedPair location, State state) {
		location = transformLocation(location);
		if (location == null || state.equals(getDefaultState()) && getCellAt(location).equals(getDefaultState())) return;
        this.cellsThatMightMove.add(location);
		if (!state.equals(getDefaultState()))
        	this.cells.put(location, state);
		else
			this.cells.remove(location);
	}

	/**
	 * Takes the board, updates all the cells, and returns the updated board. Does not affect this board.
	 * @return new board
	 */
	public Board getUpdatedBoard() {
		Board updatedBoard = makeEmptyBoardWithSameProperties();

		Set<CellUpdateTask> tasks = new HashSet<>();

		for (OrderedPair orderedPair : this.cellsThatMightMove) {
			tasks.add(new CellUpdateTask(orderedPair, this, updatedBoard));
			tasks.addAll(
					this.wakeUpNeighborhood
							.getAll()
							.stream()
							.map(orderedPair::add)
							.map(this::transformLocation)
                            .filter(Objects::nonNull)
							.map(location -> new CellUpdateTask(location, this, updatedBoard))
							.toList()
			);
		}

        updatedBoard.cells = new HashMap<>(this.cells);

		try {
			CellUpdateTask.executorService.invokeAll(tasks);
		} catch (InterruptedException e) {
			Launcher.errorWindow(e);
		}

		/*for (CellUpdateTask task : tasks) {
			task.run();
		}*/

		return updatedBoard;
	}

	/**
	 * Gets the neighbors of the current cell
	 * @param neighborhood neighborhood to check
	 * @param location location of cell to check around
	 * @return cell states of neighbors
	 */
    public Collection<State> getNeighborsOf(Neighborhood neighborhood, OrderedPair location) {
		return neighborhood
				.getAll()
				.stream()
				.map(location::add)
				.map(this::getCellAt)
				.toList();
	}

	/**
	 * Get the default cell state
	 * @return {@link #defaultState}
	 * @see #Board
	 */
	public State getDefaultState() {
		return this.defaultState;
	}

    public int getNumberOfCellsThatMightMove() {
        return this.cellsThatMightMove.size();
    }

	public boolean isToBeUpdated(OrderedPair location) {
		return this.cellsThatMightMove.contains(location);
	}
}

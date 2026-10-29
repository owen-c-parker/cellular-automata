package engine;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class Automaton {
	private Board board = new Board(getDefaultState());

	public abstract String getDisplayableName();

	private void updateAllListeners() {
		this.updateListeners.forEach(Runnable::run);
	}

	public final void step() {
		this.board = this.board.getUpdatedBoard(getWakeUpNeighborhood());
		updateAllListeners();
	}

	protected Collection<OrderedPair> getWakeUpNeighborhood() {
		return Board.MOORE_NEIGHBORHOOD;
	}

	public final void setCellAt(OrderedPair location, State state) {
		this.board.setCellAt(location, state);
		updateAllListeners();
	}

	List<Runnable> updateListeners = new LinkedList<>();

	public final void registerUpdateListener(Runnable runnable) {
		this.updateListeners.add(runnable);
	}

	public abstract List<State> getSelectableForPainting();

	public abstract State getDefaultState();

	public final State getCellAt(OrderedPair orderedPair) {
		return this.board.getCellAt(orderedPair);
	}

	@Override
	public final String toString() {
		return getDisplayableName();
	}

	public int getActiveCellCount() {
		return this.board.cells.size();
	}

	public void clear() {
		this.board = new Board(getDefaultState());
		updateAllListeners();
	}
}

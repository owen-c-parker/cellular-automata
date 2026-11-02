package automata;

import engine.*;

import java.awt.*;
import java.util.List;

public abstract class Lifelike extends Automaton {
	private State ON;
	private State OFF;

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.MOORE_NEIGHBORHOOD;
	}

	private int countAliveNeighbors(Board board, OrderedPair location) {
		int aliveCount = 0;
		for (State neighbor : board.getNeighborsOf(getWakeUpNeighborhood(), location))
			if (neighbor.equals(this.ON))
				aliveCount++;
		return aliveCount;
	}

	private State getOnState() {
		if (this.ON == null)
			this.ON = new State(Color.LIGHT_GRAY, "Alive") {
				@Override
				public State getNewState(Board board, OrderedPair location) {
					return getSurviveNumbers().contains(countAliveNeighbors(board, location)) ? getOnState() : getOffState();
				}
			};
		return this.ON;
	}

	private State getOffState() {
		if (this.OFF == null)
			this.OFF = new State(Color.DARK_GRAY, "Dead") {
				@Override
				public State getNewState(Board board, OrderedPair location) {
					return getBirthNumbers().contains(countAliveNeighbors(board, location)) ? getOnState() : getOffState();
				}
			};
		return this.OFF;
	}

	@Override
	public State getDefaultState() {
		return getOffState();
	}

	@Override
	public CursorPlaceable getSelectedByDefault() {
		return getOnState();
	}

	@Override
	public List<State> getSelectableCellStates() {
		return List.of(getOffState(), getOnState());
	}

	protected abstract List<Integer> getBirthNumbers();
	protected abstract List<Integer> getSurviveNumbers();
}

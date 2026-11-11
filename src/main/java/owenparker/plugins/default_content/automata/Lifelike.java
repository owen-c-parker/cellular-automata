package owenparker.plugins.default_content.automata;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;

import java.util.List;

public abstract class Lifelike extends Automaton {
	private State ON;

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
			this.ON = new State(ColorPalette.Slot.FOREGROUND, "Alive") {
				@Override
				public State getNewState(Board board, OrderedPair location) {
					return getSurviveNumbers().contains(countAliveNeighbors(board, location)) ? getOnState() : getDefaultState();
				}
			};
		return this.ON;
	}

	@Override
	public State generateDefaultState() {
		return new State(ColorPalette.Slot.BACKGROUND, "Dead") {
			@Override
			public State getNewState(Board board, OrderedPair location) {
				return getBirthNumbers().contains(countAliveNeighbors(board, location)) ? getOnState() : this;
			}
		};
	}

	@Override
	public CursorPlaceable getSelectedByDefault() {
		return getOnState();
	}

	@Override
	public List<State> getSelectableCellStates() {
		return List.of(getDefaultState(), getOnState());
	}

	protected abstract List<Integer> getBirthNumbers();
	protected abstract List<Integer> getSurviveNumbers();
}

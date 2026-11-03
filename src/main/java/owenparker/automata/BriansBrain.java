package owenparker.automata;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;

import java.util.Collection;
import java.util.List;

public class BriansBrain extends Automaton {
	private static final State ON = new State(ColorPalette.Slot.FOREGROUND, "On") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return DYING;
		}
	};

	private static final State DYING = new State(ColorPalette.Slot.MIDDLE, "Dying") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return OFF;
		}
	};

	private static final State OFF = new State(ColorPalette.Slot.BACKGROUND, "Off") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			int onCount = 0;
			Collection<State> states = board.getNeighborsOf(Neighborhood.MOORE_NEIGHBORHOOD, location);
			for (State state : states) {
				if (state.equals(ON)) onCount++;
			}
			return onCount == 2 ? ON : OFF;
		}
	};

	@Override
	public String getDisplayableName() {
		return "Brian's Brain";
	}

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.MOORE_NEIGHBORHOOD;
	}

	@Override
	public List<State> getSelectableCellStates() {
		return List.of(
				OFF,
				DYING,
				ON
		);
	}

	@Override
	public CursorPlaceable getSelectedByDefault() {
		return ON;
	}

	@Override
	public State getDefaultState() {
		return OFF;
	}
}

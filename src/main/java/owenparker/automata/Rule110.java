package owenparker.automata;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;

import java.util.List;

public class Rule110 extends Automaton {
	private static final State ZERO = new State(ColorPalette.Slot.BACKGROUND, "Zero") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			int integer =
					(board.getCellAt(location.add(OrderedPair.NORTH_EAST)).equals(ONE) ? 4 : 0) +
					(board.getCellAt(location.add(OrderedPair.NORTH))     .equals(ONE) ? 2 : 0) +
					(board.getCellAt(location.add(OrderedPair.NORTH_WEST)).equals(ONE) ? 1 : 0);

			return (List.of(1, 2, 3, 5, 6).contains(integer)) ? ONE : ZERO;
		}
	};

	private static final State ONE = new State(ColorPalette.Slot.FOREGROUND, "One") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return ONE;
		}
	};

	@Override
	public String getDisplayableName() {
		return "Rule 110";
	}

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.MOORE_NEIGHBORHOOD;
	}

	@Override
	public List<State> getSelectableCellStates() {
		return List.of(
				ZERO,
				ONE
		);
	}

	@Override
	public State getDefaultState() {
		return ZERO;
	}
}

package automata;

import engine.Automaton;
import engine.Board;
import engine.OrderedPair;
import engine.State;

import java.awt.*;
import java.util.List;

public class Rule110 extends Automaton {
	private static final State ZERO = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			int integer =
					(board.getCellAt(location.add(OrderedPair.NORTH_EAST)).equals(ONE) ? 4 : 0) +
					(board.getCellAt(location.add(OrderedPair.NORTH))     .equals(ONE) ? 2 : 0) +
					(board.getCellAt(location.add(OrderedPair.NORTH_WEST)).equals(ONE) ? 1 : 0);

			return (List.of(1, 2, 3, 5, 6).contains(integer)) ? ONE : ZERO;
		}

		@Override
		public Color getColor() {
			return Color.LIGHT_GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "Zero";
		}
	};

	private static final State ONE = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return ONE;
		}

		@Override
		public Color getColor() {
			return Color.DARK_GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "One";
		}
	};

	@Override
	public String getDisplayableName() {
		return "Rule 110";
	}

	@Override
	public List<State> getSelectableForPainting() {
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

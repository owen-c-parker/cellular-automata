package automata;

import engine.Automaton;
import engine.Board;
import engine.OrderedPair;
import engine.State;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class WireWorld extends Automaton {
	private static final State NOTHING = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return NOTHING;
		}

		@Override
		public Color getColor() {
			return Color.DARK_GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "Empty";
		}
	};

	private static final State WIRE = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> states = board.getNeighborsOf(Board.MOORE_NEIGHBORHOOD, location);
			int numberOfHeads = 0;
			for (State state : states) {
				if (state.equals(HEAD))
					numberOfHeads++;
			}
			return numberOfHeads == 1 || numberOfHeads == 2 ? HEAD : WIRE;
		}

		@Override
		public Color getColor() {
			return Color.LIGHT_GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "Wire";
		}
	};

	private static final State HEAD = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return TAIL;
		}

		@Override
		public Color getColor() {
			return Color.RED;
		}

		@Override
		public String getDisplayableName() {
			return "Spark head";
		}
	};

	private static final State TAIL = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return WIRE;
		}

		@Override
		public Color getColor() {
			return Color.GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "Spark tail";
		}
	};

	public WireWorld() {
		super(NOTHING);
	}

	@Override
	public String getDisplayableName() {
		return "WireWorld";
	}

	@Override
	public List<State> getSelectableForPainting() {
		return List.of(
				NOTHING,
				WIRE,
				HEAD,
				TAIL
		);
	}
}

package automata;

import engine.Automaton;
import engine.Board;
import engine.OrderedPair;
import engine.State;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class Wireworld extends Automaton {
	private static final State NOTHING = new State(Color.DARK_GRAY, "Empty") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return NOTHING;
		}
	};

	private static final State WIRE = new State(Color.LIGHT_GRAY, "Wire") {
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
	};

	private static final State HEAD = new State(Color.RED, "Electron head") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return TAIL;
		}
	};

	private static final State TAIL = new State(Color.GRAY, "Electron tail") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return WIRE;
		}
	};

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

	@Override
	public State getDefaultState() {
		return NOTHING;
	}
}

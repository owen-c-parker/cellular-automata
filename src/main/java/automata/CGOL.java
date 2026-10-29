package automata;

import engine.Automaton;
import engine.Board;
import engine.OrderedPair;
import engine.State;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class CGOL extends Automaton {
	public static final State DEAD = new State(Color.DARK_GRAY, "Dead") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> states = board.getNeighborsOf(Board.MOORE_NEIGHBORHOOD, location);

			int aliveCount = 0;
			for (State state : states) {
				if (state.equals(ALIVE))
					aliveCount++;
			}
			if (aliveCount == 3)
				return ALIVE;
			return DEAD;
		}
	};

	public static final State ALIVE = new State(Color.LIGHT_GRAY, "Alive") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> states = board.getNeighborsOf(Board.MOORE_NEIGHBORHOOD, location);
			int aliveCount = 0;
			for (State state : states) {
				if (state.equals(ALIVE))
					aliveCount++;
			}
			if (aliveCount == 2 || aliveCount == 3)
				return ALIVE;
			return DEAD;
		}
	};

	public CGOL() {
		super(Board.MOORE_NEIGHBORHOOD);
	}

	@Override
	public String getDisplayableName() {
		return "Conway's Game of Life";
	}

	@Override
	public List<State> getSelectableForPainting() {
		return List.of(DEAD, ALIVE);
	}

	@Override
	public State getDefaultState() {
		return DEAD;
	}
}

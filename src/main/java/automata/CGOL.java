package automata;

import engine.Automaton;
import engine.Board;
import engine.OrderedPair;
import engine.State;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class CGOL extends Automaton {
	public static final State DEAD = new State() {
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

		@Override
		public Color getColor() {
			return Color.DARK_GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "Dead";
		}
	};

	public static final State ALIVE = new State() {
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

		@Override
		public Color getColor() {
			return Color.LIGHT_GRAY;
		}

		@Override
		public String getDisplayableName() {
			return "Alive";
		}
	};

	public CGOL() {
		super(DEAD);
	}

	@Override
	public String getDisplayableName() {
		return "Conway's Game of Life";
	}

	@Override
	public List<State> getSelectableForPainting() {
		return List.of(DEAD, ALIVE);
	}
}

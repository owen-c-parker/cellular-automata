package automata;

import engine.Automaton;
import engine.Board;
import engine.OrderedPair;
import engine.State;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class KnightCGOL extends Automaton {
	private static final State DEAD = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> neighbors = board.getNeighborsOf(Board.KNIGHT_NEIGHBORHOOD, location);
			int livingCount = 0;
			for (State neighbor : neighbors) {
				if (neighbor.equals(ALIVE))
					livingCount++;
			}
			return livingCount == 3 ? ALIVE : DEAD;
		}

		@Override
		public Color getColor() {
			return CGOL.DEAD.getColor();
		}

		@Override
		public String getDisplayableName() {
			return CGOL.DEAD.getDisplayableName();
		}
	};

	private static final State ALIVE = new State() {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> neighbors = board.getNeighborsOf(Board.KNIGHT_NEIGHBORHOOD, location);
			int livingCount = 0;
			for (State neighbor : neighbors) {
				if (neighbor.equals(ALIVE))
					livingCount++;
			}
			return (livingCount == 3 || livingCount == 2) ? ALIVE : DEAD;
		}

		@Override
		public Color getColor() {
			return CGOL.ALIVE.getColor();
		}

		@Override
		public String getDisplayableName() {
			return CGOL.ALIVE.getDisplayableName();
		}
	};

	public KnightCGOL() {
		super(DEAD);
	}

	@Override
	public String getDisplayableName() {
		return "Knight CGOL";
	}

	@Override
	public List<State> getSelectableForPainting() {
		return List.of(
				DEAD,
				ALIVE
		);
	}
}

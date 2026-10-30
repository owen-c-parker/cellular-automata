package automata;

import engine.*;

import java.util.Collection;
import java.util.List;

public class KnightCGOL extends Automaton {
	private static final State DEAD = new State(CGOL.DEAD.getColor(), CGOL.DEAD.getDisplayableName()) {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> neighbors = board.getNeighborsOf(Neighborhood.KNIGHT_NEIGHBORHOOD, location);
			int livingCount = 0;
			for (State neighbor : neighbors) {
				if (neighbor.equals(ALIVE))
					livingCount++;
			}
			return livingCount == 3 ? ALIVE : DEAD;
		}
	};

	private static final State ALIVE = new State(CGOL.ALIVE.getColor(), CGOL.ALIVE.getDisplayableName()) {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> neighbors = board.getNeighborsOf(Neighborhood.KNIGHT_NEIGHBORHOOD, location);
			int livingCount = 0;
			for (State neighbor : neighbors) {
				if (neighbor.equals(ALIVE))
					livingCount++;
			}
			return (livingCount == 3 || livingCount == 2) ? ALIVE : DEAD;
		}
	};

	public KnightCGOL() {
		super(Neighborhood.KNIGHT_NEIGHBORHOOD);
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

	@Override
	public State getDefaultState() {
		return DEAD;
	}
}

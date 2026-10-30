package automata;

import engine.*;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class BriansBrain extends Automaton {
	private static final State ON = new State(Color.LIGHT_GRAY, "On") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return DYING;
		}
	};

	private static final State DYING = new State(Color.GRAY, "Dying") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return OFF;
		}
	};

	private static final State OFF = new State(Color.DARK_GRAY, "Off") {
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

	public BriansBrain() {
		super(Neighborhood.MOORE_NEIGHBORHOOD);
	}

	@Override
	public String getDisplayableName() {
		return "Brian's Brain";
	}

	@Override
	public List<State> getSelectableForPainting() {
		return List.of(
				OFF,
				DYING,
				ON
		);
	}

	@Override
	public State getDefaultState() {
		return OFF;
	}
}

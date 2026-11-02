package automata;

import engine.*;
import engine.colors.ColorPalette;

import java.util.Collection;
import java.util.List;

public class Wireworld extends Automaton {
	private static final State NOTHING = new State(ColorPalette.Slot.BACKGROUND, "Empty") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return NOTHING;
		}
	};

	private static final State WIRE = new State(ColorPalette.Slot.FOREGROUND, "Wire") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			Collection<State> states = board.getNeighborsOf(Neighborhood.MOORE_NEIGHBORHOOD, location);
			int numberOfHeads = 0;
			for (State state : states) {
				if (state.equals(HEAD))
					numberOfHeads++;
			}
			return numberOfHeads == 1 || numberOfHeads == 2 ? HEAD : WIRE;
		}
	};

	private static final State HEAD = new State(ColorPalette.Slot.ACTIVE_1, "Electron head") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return TAIL;
		}
	};

	private static final State TAIL = new State(ColorPalette.Slot.DISSIPATING_1, "Electron tail") {
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
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.MOORE_NEIGHBORHOOD;
	}

	@Override
	public List<State> getSelectableCellStates() {
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

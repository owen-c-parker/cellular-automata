package owenparker.plugins.default_content.automata;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementaryAutomaton extends Automaton {
	protected abstract int getRuleNumber();

	private final List<Integer> ruleList = generateRuleList(getRuleNumber());

	private List<Integer> generateRuleList(int ruleNumber) {
		List<Integer> list = new ArrayList<>();
		for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
			if (((ruleNumber >> bitIndex) & 1) == 1)
				list.add(bitIndex);
		}
		return list;
	}

	private State getZero() {
		if (this.ZERO == null)
			this.ZERO = new State(ColorPalette.Slot.BACKGROUND, "Zero") {
				@Override
				public State getNewState(Board board, OrderedPair location) {
					int integer =
							(board.getCellAt(location.add(OrderedPair.NORTH_EAST)).equals(ONE) ? 1 : 0) +
									(board.getCellAt(location.add(OrderedPair.NORTH))     .equals(ONE) ? 2 : 0) +
									(board.getCellAt(location.add(OrderedPair.NORTH_WEST)).equals(ONE) ? 4 : 0);

					return (ElementaryAutomaton.this.ruleList.contains(integer)) ? ONE : this;
				}
			};
		return this.ZERO;
	}

	private State ZERO;

	private static final State ONE = new State(ColorPalette.Slot.FOREGROUND, "One") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return ONE;
		}
	};

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.MOORE_NEIGHBORHOOD;
	}

	@Override
	public List<State> getSelectableCellStates() {
		return List.of(
				this.ZERO,
				ONE
		);
	}

	@Override
	public State getDefaultState() {
		return getZero();
	}

	@Override
	public String getDisplayableName() {
		return String.format("Rule %d", getRuleNumber());
	}
}

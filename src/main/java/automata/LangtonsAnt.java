package automata;

import engine.*;

import java.awt.*;
import java.util.List;

public class LangtonsAnt extends Automaton {

	private static final AgentBehavior ANT = new AgentBehavior() {
		private OrderedPair direction = OrderedPair.EAST;

		@Override
		public OrderedPair step(Board board, OrderedPair location) {
			if (board.getCellAt(location).equals(OFF))
				this.direction = this.direction.rotateLeft();
			else
				this.direction = this.direction.rotateRight();

			board.setCellAt(location, board.getCellAt(location).equals(ON) ? OFF : ON);
			return location.add(this.direction);
		}
	};

	private static final State OFF = new State(Color.DARK_GRAY, "Off") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return OFF;
		}
	};

	private static final State ON = new State(Color.LIGHT_GRAY, "On") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return ON;
		}
	};

	public LangtonsAnt() {
		super(Neighborhood.EMPTY);
		this.addAgent(new Agent(new OrderedPair(0, 0), ANT));
	}

	@Override
	public List<AgentBehavior> getSelectableAgentBehaviors() {
		return List.of(
			ANT
		);
	}

	@Override
	public String getDisplayableName() {
		return "Langton's Ant";
	}

	@Override
	public List<State> getSelectableCellStates() {
		return List.of(
				OFF,
				ON
		);
	}

	@Override
	public State getDefaultState() {
		return OFF;
	}
}

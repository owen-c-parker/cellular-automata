package automata;

import engine.*;

import java.awt.*;
import java.util.List;

public class LangtonsAnt extends Automaton {

	private static final AgentFactory ANT = new AgentFactory() {
        @Override
        public String getDisplayableName() {
            return "Ant";
        }

        @Override
        public Agent buildAgent(OrderedPair position) {
            return new Agent(position) {
                private OrderedPair direction = OrderedPair.EAST;

                @Override
                public void step(Board board) {

                    OrderedPair location = getPosition();

                    if (board.getCellAt(location).equals(OFF))
                        this.direction = this.direction.rotateLeft();
                    else
                        this.direction = this.direction.rotateRight();

                    board.setCellAt(location, board.getCellAt(location).equals(ON) ? OFF : ON);

                    setPosition(location.add(this.direction));
                }

                @Override
                public void draw(Graphics g) {
                    g.setColor(Color.RED);

                    Rectangle bounds = g.getClipBounds();

                    OrderedPair center = new OrderedPair(bounds.width / 2, bounds.height / 2);

                    OrderedPair endOfLine = center.add(this.direction.scale(center.x()));

                    g.drawLine(center.x(), center.y(), endOfLine.x(), endOfLine.y());
                }
            };
        }
    };

    @Override
    public CursorPlaceable getSelectedByDefault() {
        return ANT;
    }

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.EMPTY;
	}

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

	@Override
	public List<AgentFactory> getSelectableAgentFactories() {
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

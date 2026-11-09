package owenparker.automata;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;

import java.awt.*;
import java.util.List;

public class LangtonsAnt extends Automaton {

	private static final Image ANT_IMAGE = ImageHelper.readImageFromResources("images/ant.png");

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
					if ((g instanceof Graphics2D g2d) && ANT_IMAGE != null) {
						ImageHelper.drawImageToFillGraphicsContext(g2d, ANT_IMAGE, this.direction.getAngle());
						return;
					}
					super.draw(g);
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

	private static final State OFF = new State(ColorPalette.Slot.BACKGROUND, "Off") {
		@Override
		public State getNewState(Board board, OrderedPair location) {
			return OFF;
		}
	};

	private static final State ON = new State(ColorPalette.Slot.FOREGROUND, "On") {
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

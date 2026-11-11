package owenparker.plugins.default_content.automata.turmites;

import owenparker.engine.*;

import java.awt.*;
import java.util.List;

public class TurmiteAgentFactory implements AgentFactory {
	private static final Image ANT_IMAGE = ImageHelper.readImageFromResources("images/ant.png");

	private final String name;
	private final TurnDecision[] turnDecisions;
	private final List<TurmiteState> states;

	public TurmiteAgentFactory(String name, TurnDecision[] turnDecisions, List<TurmiteState> states) {
		this.name = name;
		this.turnDecisions = turnDecisions;
		this.states = states;
	}

	@Override
	public Agent buildAgent(OrderedPair position) {
		return new Agent(position) {
			private OrderedPair direction = OrderedPair.WEST;

			@Override
			public void draw(Graphics g) {
				if ((g instanceof Graphics2D g2d) && ANT_IMAGE != null) {
					ImageHelper.drawImageToFillGraphicsContext(g2d, ANT_IMAGE, this.direction.getAngle());
					return;
				}
				super.draw(g);
			}

			@Override
			public void step(Board board) {
				State state = board.getCellAt(getPosition());

				if (!(state instanceof TurmiteState turmiteState))
					return;

				int index = turmiteState.getIndex();
				TurnDecision decision = TurmiteAgentFactory.this.turnDecisions[index];

				this.direction = switch (decision) {
					case NONE -> this.direction;
					case LEFT -> this.direction.rotateLeft();
					case RIGHT -> this.direction.rotateRight();
					case REVERSE -> this.direction.rotateLeft().rotateLeft();
				};

				board.setCellAt(getPosition(), TurmiteAgentFactory.this.states.get((index + 1) % TurmiteAgentFactory.this.states.size()));

				setPosition(getPosition().add(this.direction));
			}
		};
	}

	@Override
	public String getDisplayableName() {
		return this.name;
	}
}

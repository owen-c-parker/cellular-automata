package automata;

import engine.*;
import engine.colors.ColorPalette;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ChessWander extends Automaton {
	private static final AgentFactory KNIGHT = getChessPieceFactory(Neighborhood.KNIGHT_NEIGHBORHOOD.getAll(), "Knight");

	private static AgentFactory getChessPieceFactory(Collection<OrderedPair> moves, String name) {
		return new AgentFactory() {
			@Override
			public Agent buildAgent(OrderedPair position) {
				return new Agent(position) {
					private final Random random = new Random();
					@Override
					public void step(Board board) {
						List<OrderedPair> moveList = List.copyOf(moves);
						OrderedPair randomMove = moveList.get(this.random.nextInt(moveList.size()));
						setPosition(getPosition().add(randomMove));
					}
				};
			}

			@Override
			public String getDisplayableName() {
				return name;
			}
		};
	}

	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.EMPTY;
	}

	@Override
	public List<AgentFactory> getSelectableAgentFactories() {
		return List.of(
				KNIGHT
		);
	}

	@Override
	public State getDefaultState() {
		return new State(ColorPalette.Slot.BACKGROUND, "Nothing") {
			@Override
			public State getNewState(Board board, OrderedPair location) {
				return this;
			}
		};
	}

	@Override
	public String getDisplayableName() {
		return "Chess Wander";
	}
}

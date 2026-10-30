package engine;

/**
 * An agent is a stateful object that has a position
 */
public final class Agent {
	private OrderedPair position;
	private final AgentBehavior behavior;

	public Agent(OrderedPair position, AgentBehavior behavior) {
		this.position = position;
		this.behavior = behavior;
	}

	public void step(Board board) {
		this.position = this.behavior.step(board, this.position);
	}
}

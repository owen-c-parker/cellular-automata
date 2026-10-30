package engine;

public interface AgentBehavior {
	/**
	 * @param board The current board
	 * @param location Where the agent is
	 * @return Where the agent should be after the step
	 */
	OrderedPair step(Board board, OrderedPair location);
}

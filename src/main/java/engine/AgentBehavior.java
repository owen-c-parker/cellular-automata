package engine;

import java.awt.*;

public interface AgentBehavior {
	/**
	 * @param board The current board
	 * @param location Where the agent is
	 * @return Where the agent should be after the step
	 */
	OrderedPair step(Board board, OrderedPair location);

    default void customDraw(Graphics g) {
        g.setColor(Color.RED);
        g.fillArc(0, 0, g.getClipBounds().width, g.getClipBounds().height, 0, 360);
    }
}

package engine;

import java.awt.*;

/**
 * An agent is a stateful object that has a position
 */
public abstract class Agent {
	private OrderedPair position;

	public Agent(OrderedPair position) {
		this.position = position;
	}

	public abstract void step(Board board);

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillArc(0, 0, g.getClipBounds().width, g.getClipBounds().height, 0, 360);
    }

    protected void setPosition(OrderedPair position) {
        this.position = position;
    }

    public OrderedPair getPosition() {
        return this.position;
    }
}

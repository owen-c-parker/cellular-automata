package owenparker.engine;

import java.awt.*;
import java.io.Serializable;

/**
 * An agent is a stateful object that has a position
 */
public abstract class Agent implements Serializable {
	private OrderedPair position;

	public Agent(OrderedPair position) {
		this.position = position;
	}

	public void stepWithRespectToBorderPolicy(Board board) {
		OrderedPair oldPosition = this.position;
		step(board);
		this.position = board.transformLocation(this.position);
		if (this.position == null)
			this.position = oldPosition;
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

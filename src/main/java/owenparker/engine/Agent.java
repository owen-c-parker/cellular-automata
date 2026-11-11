package owenparker.engine;

import java.awt.*;
import java.io.Serializable;

/**
 * An agent is a stateful object that has a position in the cell grid.
 */
public abstract class Agent implements Serializable {
	private OrderedPair position;

	/**
	 * @param position starting place
	 */
	public Agent(OrderedPair position) {
		this.position = position;
	}

	public final void stepWithRespectToBorderPolicy(Board board) {
		OrderedPair oldPosition = this.position;
		step(board);
		this.position = board.transformLocation(this.position);
		if (this.position == null)
			this.position = oldPosition;
	}

	/**
	 * Take action during a step. Occurs after the cells update themselves.
	 * @param board current board state
	 * @see #setPosition
	 * @see #getPosition
	 */
	public abstract void step(Board board);

	/**
	 * @param g graphics restrained to the square this {@link Agent} occupies
	 * @see State#draw
	 */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillArc(0, 0, g.getClipBounds().width, g.getClipBounds().height, 0, 360);
    }

	/**
	 * @param position new position
	 * @see #move
	 */
    public void setPosition(OrderedPair position) {
        this.position = position;
    }

	/**
	 * @param offset offset of current position
	 * @see #setPosition
	 */
	public void move(OrderedPair offset) {
		setPosition(getPosition().add(offset));
	}

	/**
	 * @return current position
	 */
    public OrderedPair getPosition() {
        return this.position;
    }

}

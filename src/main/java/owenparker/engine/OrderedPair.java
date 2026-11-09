package owenparker.engine;

import java.awt.*;
import java.io.Serializable;

public record OrderedPair(int x, int y) implements Serializable {
    public static final OrderedPair UNIT = new OrderedPair(1, 1);

    public OrderedPair(Dimension dimension) {
        this(dimension.width, dimension.height);
    }

    public OrderedPair negate() {
		return new OrderedPair(-x(), -y());
	}

	public OrderedPair add(int x, int y) {
		return new OrderedPair(x() + x, y() + y);
	}

	public OrderedPair add(OrderedPair other) {
		return add(other.x, other.y);
	}

	public OrderedPair subtract(int x, int y) {
		return new OrderedPair(x() - x, y() - y);
	}

	public OrderedPair subtract(OrderedPair other) {
		return subtract(other.x, other.y);
	}

	public OrderedPair scale(Number scalar) {
		return new OrderedPair((int) (scalar.doubleValue() * x()), (int) (y() * scalar.doubleValue()));
	}

	public OrderedPair divide(Number divisor) {
		return scale( 1 / divisor.doubleValue());
	}
	public static final OrderedPair UP = new OrderedPair(0, -1);
	public static final OrderedPair DOWN = new OrderedPair(0, 1);
	public static final OrderedPair LEFT = new OrderedPair(-1, 0);

	public static final OrderedPair RIGHT = new OrderedPair(1, 0);
	public static final OrderedPair NORTH = UP;
	public static final OrderedPair SOUTH = DOWN;
	public static final OrderedPair EAST = LEFT;

	public static final OrderedPair WEST = RIGHT;
	public static final OrderedPair NORTH_EAST = NORTH.add(EAST);
	public static final OrderedPair NORTH_WEST = NORTH.add(WEST);
	public static final OrderedPair SOUTH_EAST = SOUTH.add(EAST);

	public static final OrderedPair SOUTH_WEST = SOUTH.add(WEST);

	public OrderedPair rotateRight() {
		return new OrderedPair(-y(), x());
	}

	public OrderedPair rotateLeft() {
		return new OrderedPair(y(), -x());
	}

	public double getAngle() {
		return Math.atan2(y(), x());
	}

	public OrderedPair mod(OrderedPair divisor) {
		OrderedPair dividend = this;
		while (dividend.x < 0) {
			dividend = new OrderedPair(dividend.x + divisor.x, dividend.y);
		}
		while (dividend.y < 0) {
			dividend = new OrderedPair(dividend.x, dividend.y + divisor.y());
		}
		return new OrderedPair(dividend.x % divisor.x, dividend.y % divisor.y);
	}
}

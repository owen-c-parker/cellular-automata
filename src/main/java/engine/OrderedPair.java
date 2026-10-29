package engine;

public record OrderedPair(int x, int y) {

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
}

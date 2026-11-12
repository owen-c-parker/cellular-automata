package owenparker.engine;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BiFunction;

public record OrderedPair(BigInteger x, BigInteger y) implements Serializable {
    public static final OrderedPair UNIT = new OrderedPair(1, 1);
	public static final OrderedPair ORIGIN = new OrderedPair(0, 0);

	public OrderedPair(int x, int y) {
		this(BigInteger.valueOf(x), BigInteger.valueOf(y));
	}

	public OrderedPair(BigDecimal x, BigDecimal y) {
		this(x.toBigInteger(), y.toBigInteger());
	}

	public OrderedPair(Dimension dimension) {
        this(dimension.width, dimension.height);
    }

	public OrderedPair(MouseEvent e) {
		this(e.getX(), e.getY());
	}

	public OrderedPair operate(BiFunction<BigInteger, BigInteger, BigInteger> operation, OrderedPair operand) {
		return new OrderedPair(operation.apply(this.x, operand.x), operation.apply(this.y, operand.y));
	}

	public OrderedPair negate() {
		return new OrderedPair(x().negate(), y().negate());
	}

	public OrderedPair add(int x, int y) {
		return add(new OrderedPair(x, y));
	}

	public OrderedPair add(OrderedPair other) {
		return operate(BigInteger::add, other);
	}

	public OrderedPair subtract(int x, int y) {
		return subtract(new OrderedPair(x, y));
	}

	public OrderedPair subtract(OrderedPair other) {
		return operate(BigInteger::subtract, other);
	}

	public OrderedPair scale(Number scalar) {
		BigDecimal bigScalar = new BigDecimal(scalar.doubleValue());
		return new OrderedPair(
				new BigDecimal(x()).multiply(bigScalar),
				new BigDecimal(y()).multiply(bigScalar)
		);
	}

	public OrderedPair divide(Number divisor) {
		return scale( 1.0 / divisor.doubleValue());
	}

	public static final OrderedPair UP = new OrderedPair(0, -1);
	public static final OrderedPair DOWN = new OrderedPair(0, 1);
	public static final OrderedPair LEFT = new OrderedPair(-1, 0);
    public static final OrderedPair RIGHT = new OrderedPair(1, 0);

    public static final OrderedPair NORTH = UP;
	public static final OrderedPair SOUTH = DOWN;
	public static final OrderedPair EAST = RIGHT;
	public static final OrderedPair WEST = LEFT;

	public static final OrderedPair NORTH_EAST = NORTH.add(EAST);
	public static final OrderedPair NORTH_WEST = NORTH.add(WEST);
	public static final OrderedPair SOUTH_EAST = SOUTH.add(EAST);

	public static final OrderedPair SOUTH_WEST = SOUTH.add(WEST);

	public OrderedPair rotateRight() {
		return new OrderedPair(y().negate(), x());
	}

	public OrderedPair rotateLeft() {
		return new OrderedPair(y(), x().negate());
	}

	public double getAngle() {
		return Math.atan2(y().longValue(), x().longValue());
	}

	public OrderedPair mod(OrderedPair divisor) {
		BigInteger xMod = this.x().divideAndRemainder(divisor.x)[1];
		BigInteger yMod = this.y().divideAndRemainder(divisor.y)[1];

		if (xMod.signum() < 0)
			xMod = xMod.add(divisor.x);

		if (yMod.signum() < 0)
			yMod = yMod.add(divisor.y);

		return new OrderedPair(xMod, yMod);
	}

    @Override
    public String toString() {
        return String.format("(%s, %s)", x(), y());
    }

	public int xInt() {
		return x().intValue();
	}

	public int yInt() {
		return y().intValue();
	}
}

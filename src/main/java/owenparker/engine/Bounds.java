package owenparker.engine;

import java.math.BigInteger;

public record Bounds(
		OrderedPair a,
		OrderedPair b
) {
	public OrderedPair getTopLeft() {
		return new OrderedPair(min(a().x(), b().x()), min(a().y(), b().y()));
	}

	public OrderedPair getBottomRight() {
		return new OrderedPair(max(a().x(), b().x()), max(a().y(), b().y()));
	}

	private BigInteger min(BigInteger a, BigInteger b) {
		if (a.compareTo(b) < 0)
			return a;
		return b;
	}

	private BigInteger max(BigInteger a, BigInteger b) {
		if (a.compareTo(b) > 0)
			return a;
		return b;
	}
}

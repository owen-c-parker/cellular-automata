package owenparker.engine;

public record Bounds(
		OrderedPair a,
		OrderedPair b
) {
	public OrderedPair getTopLeft() {
		return new OrderedPair(Math.min(a().x(), b().x()), Math.min(a().y(), b().y()));
	}

	public OrderedPair getBottomRight() {
		return new OrderedPair(Math.max(a().x(), b().x()), Math.max(a().y(), b().y()));
	}
}

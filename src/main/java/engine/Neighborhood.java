package engine;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * A collection of ordered pairs representing a neighborhood around a cell
 */
public class Neighborhood {
	private final Collection<OrderedPair> orderedPairs;

	public Neighborhood(OrderedPair... orderedPairs) {
		this(Arrays.stream(orderedPairs).toList());
	}

	public Neighborhood(Collection<OrderedPair> orderedPairs) {
		this.orderedPairs = Collections.unmodifiableCollection(orderedPairs);
	}

	public Neighborhood union(Neighborhood other) {
		HashSet<OrderedPair> union = new HashSet<>(this.orderedPairs);
		union.addAll(other.orderedPairs);
		return new Neighborhood(union);
	}

	public Neighborhood intersection(Neighborhood other) {
		HashSet<OrderedPair> intersection = new HashSet<>(this.orderedPairs);
		intersection.removeAll(other.orderedPairs);
		return new Neighborhood(intersection);
	}

	public Collection<OrderedPair> getAll() {
		return this.orderedPairs;
	}
}

package engine;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static engine.OrderedPair.*;

/**
 * A collection of ordered pairs representing a neighborhood around a cell
 */
public class Neighborhood {
	public static final Neighborhood MOORE_NEIGHBORHOOD = new Neighborhood(
			NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
			);

	public static final Neighborhood VON_NEUMANN_NEIGHBORHOOD = new Neighborhood(
			NORTH, SOUTH, EAST, WEST
	);

	public static final Neighborhood KNIGHT_NEIGHBORHOOD = new Neighborhood(
			NORTH.add(NORTH_EAST), NORTH.add(NORTH_WEST),
			EAST.add(NORTH_EAST) , EAST.add(SOUTH_EAST),
			WEST.add(NORTH_WEST) , WEST.add(SOUTH_WEST),
			SOUTH.add(SOUTH_EAST), SOUTH.add(SOUTH_WEST)
	);
	public static final Neighborhood EMPTY = new Neighborhood();

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

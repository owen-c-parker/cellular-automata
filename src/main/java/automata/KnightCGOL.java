package automata;

import engine.Neighborhood;

public class KnightCGOL extends CGOL {
	@Override
	protected Neighborhood getWakeUpNeighborhood() {
		return Neighborhood.KNIGHT_NEIGHBORHOOD;
	}

	@Override
	public String getDisplayableName() {
		return "Knight's Game of Life";
	}
}

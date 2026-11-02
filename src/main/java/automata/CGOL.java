package automata;

import java.util.List;

public class CGOL extends Lifelike {
	@Override
	protected List<Integer> getBirthNumbers() {
		return List.of(3);
	}

	@Override
	protected List<Integer> getSurviveNumbers() {
		return List.of(2, 3);
	}

	@Override
	public String getDisplayableName() {
		return "Conway's Game of Life";
	}
}

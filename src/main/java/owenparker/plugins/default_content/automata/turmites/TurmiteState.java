package owenparker.plugins.default_content.automata.turmites;

import owenparker.engine.Board;
import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.engine.colors.ColorEvaluator;
import owenparker.engine.colors.ColorPalette;
import owenparker.engine.colors.IndexedColor;
import owenparker.engine.colors.PaletteColor;

public class TurmiteState extends State {
	private final int index;

	public int getIndex() {
		return this.index;
	}

	public TurmiteState(int index) {
		super(generateColor(index), String.format("State #%d", index));
		this.index = index;
	}

	private static ColorEvaluator generateColor(int index) {
		if (index == 0)
			return new PaletteColor(ColorPalette.Slot.BACKGROUND);
		if (index == 1)
			return new PaletteColor(ColorPalette.Slot.FOREGROUND);
		else
			return new IndexedColor(index - 2);
	}

	@Override
	public State getNewState(Board board, OrderedPair location) {
		return this;
	}
}

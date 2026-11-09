package owenparker.gui.menu_bar.tools;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.gui.GlobalReference;

import java.util.List;
import java.util.Random;

public class NoiseMenuItem extends ToolMenuItem {
    public NoiseMenuItem(GlobalReference globalReference) {
        super(globalReference, "Fill screen with random cells", () -> {
            Random random = new Random();
            for (OrderedPair location : globalReference.gridDisplay.getCellLocationsOnScreen()) {
                List<State> selectable = globalReference.getAutomaton().getSelectableCellStates();
                State randomState = selectable.get(random.nextInt(selectable.size()));
                globalReference.getAutomaton().setCellAt(location, randomState);
            }
        });
    }
}

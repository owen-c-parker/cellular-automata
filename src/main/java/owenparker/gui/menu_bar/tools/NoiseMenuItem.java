package owenparker.gui.menu_bar.tools;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.SimulationContext;

import java.util.List;
import java.util.Random;

public class NoiseMenuItem extends ToolMenuItem {
    public NoiseMenuItem() {
        super("Fill screen with random cells", () -> {
            Random random = new Random();
            for (OrderedPair location : DisplayContext.gridDisplay.getCellLocationsOnScreen()) {
                List<State> selectable = SimulationContext.getAutomaton().getSelectableCellStates();
                State randomState = selectable.get(random.nextInt(selectable.size()));
                SimulationContext.getAutomaton().setCellAt(location, randomState);
            }
        });
    }
}

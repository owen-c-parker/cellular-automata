package owenparker.gui.menu_bar.tools;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;

public class ClearMenuItem extends ToolMenuItem {
    public ClearMenuItem() {
        super("Clear entire grid", () -> {
            SimulationContext.getAutomaton().clear();
			Events.repaint.fire();
        });
    }
}

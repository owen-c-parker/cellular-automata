package owenparker.gui.menu_bar.tools;

import owenparker.gui.GlobalReference;

public class ClearMenuItem extends ToolMenuItem {
    public ClearMenuItem(GlobalReference globalReference) {
        super(globalReference, "Clear entire grid", () -> {
            globalReference.getAutomaton().clear();
            globalReference.repaintEvent.fire();
        });
    }
}

package owenparker.gui.menu_bar.tools;

import owenparker.gui.GuiReference;

public class ClearMenuItem extends ToolMenuItem {
    public ClearMenuItem(GuiReference guiReference) {
        super(guiReference, "Clear entire grid", () -> {
            guiReference.getAutomaton().clear();
            guiReference.repaintEvent.fire();
        });
    }
}

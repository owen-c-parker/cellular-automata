package owenparker.gui.menu_bar.tools;

import owenparker.gui.GlobalReference;
import owenparker.gui.lang.Language;

public class ClearMenuItem extends ToolMenuItem {
    public ClearMenuItem(GlobalReference globalReference) {
        super(globalReference, translation(Language.Key.CLEAR_MENU_OPTION), () -> {
            globalReference.getAutomaton().clear();
            globalReference.repaintEvent.fire();
        });
    }
}

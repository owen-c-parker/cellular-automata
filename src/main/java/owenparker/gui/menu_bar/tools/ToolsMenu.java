package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.lang.Language;

import javax.swing.*;

public class ToolsMenu extends AccessorComponent<JMenu> {
    public ToolsMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.TOOLS_MENU_OPTION)));

        getComponent().add(new NoiseMenuItem(globalReference).getComponent());
        getComponent().add(new ClearMenuItem(globalReference).getComponent());
    }
}

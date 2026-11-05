package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.lang.Language;

import javax.swing.*;

public class ToolsMenu extends AccessorComponent<JMenu> {
    public ToolsMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.TOOLS_MENU_OPTION)));

        add(NoiseMenuItem::new);
        add(ClearMenuItem::new);
		add(ResetCameraMenuItem::new);
    }
}

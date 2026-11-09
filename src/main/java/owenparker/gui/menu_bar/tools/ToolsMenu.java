package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class ToolsMenu extends AccessorComponent<JMenu> {
    public ToolsMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu("Tools"));

        add(NoiseMenuItem::new);
        add(ClearMenuItem::new);
		add(ResetCameraMenuItem::new);
    }
}

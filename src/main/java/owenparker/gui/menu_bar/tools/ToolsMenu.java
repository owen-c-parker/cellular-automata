package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

import javax.swing.*;

public class ToolsMenu extends AccessorComponent<JMenu> {
    public ToolsMenu(GuiReference guiReference) {
        super(guiReference, new JMenu("Tools"));

        getComponent().add(new NoiseMenuItem(guiReference).getComponent());
        getComponent().add(new ClearMenuItem(guiReference).getComponent());
    }
}

package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

import javax.swing.*;

public class ToolMenuItem extends AccessorComponent<JMenuItem> {
    public ToolMenuItem(GuiReference guiReference, String label, Runnable runnable) {
        super(guiReference, new JMenuItem(label));
        getComponent().addActionListener(_ -> {
            runnable.run();
        });
    }
}

package gui.menu_bar.tools;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;

public class ToolMenuItem extends AccessorComponent<JMenuItem> {
    public ToolMenuItem(GuiReference guiReference, String label, Runnable runnable) {
        super(guiReference, new JMenuItem(label));
        getComponent().addActionListener(_ -> {
            runnable.run();
        });
    }
}

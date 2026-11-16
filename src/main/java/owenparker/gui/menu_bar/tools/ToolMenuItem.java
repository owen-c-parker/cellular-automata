package owenparker.gui.menu_bar.tools;

import owenparker.gui.context.DisplayContext;

import javax.swing.*;

public class ToolMenuItem extends JMenuItem {
    public ToolMenuItem(String label, Runnable runnable) {
        super(label);
        addActionListener(_ -> runnable.run());
    }
}

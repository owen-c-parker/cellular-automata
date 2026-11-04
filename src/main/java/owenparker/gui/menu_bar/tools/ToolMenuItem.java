package owenparker.gui.menu_bar.tools;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class ToolMenuItem extends AccessorComponent<JMenuItem> {
    public ToolMenuItem(GlobalReference globalReference, String label, Runnable runnable) {
        super(globalReference, new JMenuItem(label));
        getComponent().addActionListener(_ -> {
            runnable.run();
        });
    }
}

package owenparker.gui.menu_bar.file;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.export.ExportWindow;

import javax.swing.*;

public class ExportMenu extends AccessorComponent<JMenuItem> {
    public ExportMenu(GlobalReference globalReference) {
        super(globalReference, new JMenuItem("Export"));

        getComponent().addActionListener(_ -> new ExportWindow(globalReference));
    }
}

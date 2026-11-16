package owenparker.gui.menu_bar.file;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.export.ExportWindow;

import javax.swing.*;

public class ExportMenu extends JMenuItem {
    public ExportMenu() {
        super("Export");

        addActionListener(_ -> new ExportWindow());
    }
}

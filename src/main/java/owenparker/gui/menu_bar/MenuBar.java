package owenparker.gui.menu_bar;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.menu_bar.about.AboutMenu;
import owenparker.gui.menu_bar.file.FileMenu;
import owenparker.gui.menu_bar.tools.ToolsMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        super();

        add(new FileMenu());
        add(new ToolsMenu());
		add(new AboutMenu());
    }
}

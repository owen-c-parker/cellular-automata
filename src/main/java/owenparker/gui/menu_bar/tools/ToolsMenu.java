package owenparker.gui.menu_bar.tools;

import owenparker.gui.context.DisplayContext;

import javax.swing.*;

public class ToolsMenu extends JMenu {
    public ToolsMenu() {
        super("Tools");

        add(new NoiseMenuItem());
        add(new ClearMenuItem());
		add(new ResetCameraMenuItem());
		add(new WarpCameraMenuItem());
    }
}

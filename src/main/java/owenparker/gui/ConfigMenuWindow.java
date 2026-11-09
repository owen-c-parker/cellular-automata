package owenparker.gui;

import owenparker.config.Configuration;

import javax.swing.*;
import java.awt.*;

public class ConfigMenuWindow extends GenericWindow {
    public ConfigMenuWindow(Configuration configuration, String title) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        getContentPane().add(configuration.getPanel());
        setTitle(title);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

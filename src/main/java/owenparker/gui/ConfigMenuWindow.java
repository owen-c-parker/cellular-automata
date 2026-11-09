package owenparker.gui;

import owenparker.config.Configuration;

import javax.swing.*;
import java.awt.*;

public class ConfigMenuWindow extends GenericWindow {
    public ConfigMenuWindow(Configuration configuration, String title) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        getContentPane().add(configuration.getPanel());
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

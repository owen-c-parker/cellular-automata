package owenparker.gui;

import owenparker.config.Configuration;

public class ConfigMenuWindow extends GenericWindow {
    public ConfigMenuWindow(Configuration configuration, String title) {
        getContentPane().add(configuration.getPanel());
        setTitle(title);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

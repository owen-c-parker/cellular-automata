package owenparker.gui.menu_bar.about;

import owenparker.gui.FileManagement;
import owenparker.gui.GenericWindow;
import owenparker.gui.Launcher;
import owenparker.plugins.Plugin;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InstalledPluginsWindow extends GenericWindow {
    public InstalledPluginsWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topInfo = new JPanel();
        topInfo.add(new JLabel("Plugins are stored in your plugins folder"));
        JButton openFolderButton = new JButton("Open folder");
        openFolderButton.addActionListener(_ -> {
            try {
                Desktop.getDesktop().open(FileManagement.getPluginsDirectory());
            } catch (IOException e) {
                Launcher.errorWindow(e);
            }
        });
        topInfo.add(openFolderButton);

        panel.add(topInfo);

        panel.add(new JSeparator());
        JPanel installedText = new JPanel();
        installedText.add(new JLabel("Installed:"));
        panel.add(installedText);

        for (Plugin plugin : Launcher.installedPlugins) {
            Panel pluginPanel = new Panel();

            pluginPanel.add(new JLabel(String.format("%s by %s", plugin.getDisplayableName(), plugin.getAuthor())));

            panel.add(pluginPanel);
        }

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

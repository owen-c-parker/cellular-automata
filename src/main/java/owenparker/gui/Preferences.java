package owenparker.gui;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.*;
import owenparker.engine.colors.ColorPalette;
import owenparker.plugins.Plugin;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Preferences extends Configuration {
    public final ConfigDropdown<ColorPalette> selectedTheme = new ConfigDropdown<>(
            "Theme",
            Launcher.installedPlugins.getFirst().getColorPalettes().getFirst(),
            Launcher.getAllOfAResource(Plugin::getColorPalettes).toArray(new ColorPalette[0])
    );

    public Preferences() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            String configAsText = convertToText();

            File configFile = Path.of(FileManagement.getMainDirectory().getPath(), "config.txt").toFile();
            try (FileWriter writer = new FileWriter(configFile)) {
                writer.write(configAsText);
            } catch (IOException e) {
                System.err.println(e);
            }
        }));
    }
}

package owenparker.gui;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.*;
import owenparker.engine.colors.ColorPalette;
import owenparker.plugins.Plugin;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Preferences extends Configuration {
    public final ConfigDropdown<ColorPalette> selectedTheme = new ConfigDropdown<>(
            "Theme",
            Launcher.installedPlugins.getFirst().getColorPalettes().getFirst(),
            Launcher.getAllOfAResource(Plugin::getColorPalettes).toArray(new ColorPalette[0])
    );
    public final ConfigDouble cellScaleToStopDrawingGridlines = new ConfigDouble("Zoom level to start drawing grid", 10.0, 1.0, 100.0);

    public Preferences() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            String configAsText = convertToText();

            try (FileWriter writer = new FileWriter(FileManagement.getConfigFile())) {
                writer.write(configAsText);
            } catch (IOException e) {
                System.err.println(e);
            }
        }));
    }
}

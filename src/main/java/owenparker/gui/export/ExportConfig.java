package owenparker.gui.export;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigDouble;
import owenparker.config.configuration_entries.ConfigDropdown;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.GlobalReference;
import owenparker.gui.Launcher;
import owenparker.plugins.Plugin;

public class ExportConfig extends Configuration {
    public final ConfigDropdown<Exporter> exporter = new ConfigDropdown<>("Format", Exporter.getExporters()[0], Exporter.getExporters());
    public final ConfigDouble scale = new ConfigDouble("Image scale", 16.0, 1.0, Double.MAX_VALUE);
    public final ConfigDropdown<ColorPalette> theme;

    public ExportConfig(GlobalReference globalReference) {
        this.theme = new ConfigDropdown<>(
                "Theme to render with",
                globalReference.getColorPalette(),
                Launcher.getAllOfAResource(Plugin::getColorPalettes).toArray(new ColorPalette[0])
        );
    }
}

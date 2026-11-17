package owenparker.gui.preferences;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigDouble;
import owenparker.config.configuration_entries.ConfigDropdown;
import owenparker.config.configuration_entries.ConfigInteger;
import owenparker.config.configuration_entries.ConfigNested;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.FileManagement;
import owenparker.gui.Launcher;
import owenparker.gui.context.Events;
import owenparker.gui.grid.gridlines.DotsGridlines;
import owenparker.gui.grid.gridlines.Gridlines;
import owenparker.gui.grid.gridlines.SolidGridlines;
import owenparker.plugins.Plugin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;

public class Preferences extends Configuration {
	private static Preferences instance;

	public final ConfigDropdown<ColorPalette> selectedTheme = new ConfigDropdown<>(
            "Theme",
            Launcher.installedPlugins.getFirst().getColorPalettes().getFirst(),
            Launcher.getAllOfAResource(Plugin::getColorPalettes).toArray(new ColorPalette[0])
    );
    public final ConfigDouble cellScaleToStopDrawingGridlines = new ConfigDouble(
			"Zoom level to start drawing grid",
			10.0,
			1.0,
			100.0
	);
	public final ConfigInteger speedSliderNotchCount = new ConfigInteger("Length of speed slider", 16, 3, Integer.MAX_VALUE);
	public ConfigDropdown<Gridlines> gridlineType = new ConfigDropdown<>("Gridline style", new Gridlines[] {
			new SolidGridlines(),
			new DotsGridlines()
	});
	public final ConfigInteger threadCount = new ConfigInteger("Thread count", 32, 1, Integer.MAX_VALUE);

	public final ConfigNested<Debug> debug = new ConfigNested<>("Debug", new Debug());

	private Preferences() {
		this.selectedTheme.addSetListener(Events.newColorPalette::fire);
		this.selectedTheme.addSetListener(Events.repaint::fire);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            String configAsText = convertToText();

            try (FileWriter writer = new FileWriter(FileManagement.getConfigFile())) {
                writer.write(configAsText);
            } catch (IOException e) {
                System.err.println(e);
            }
        }));
    }

	public static Preferences getInstance() {
		if (instance == null)
			instance = new Preferences();
		return instance;
	}
}

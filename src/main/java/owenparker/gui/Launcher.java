package owenparker.gui;

import owenparker.automaton_factories.*;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.OrderedPair;
import owenparker.engine.colors.ColorPalette;
import owenparker.plugins.Plugin;
import owenparker.plugins.PluginLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Launcher {

	public static final String APP_NAME = "Cellular Automaton Simulator";
	public static final String APP_NAME_NO_SPACES = "CellularAutomatonSimulator";

	public static final LinkedList<AutomatonFactory> automatonFactoriesFromPlugins = new LinkedList<>();
	public static final LinkedList<ColorPalette> colorPalettesFromPlugins = new LinkedList<>();
	public static final LinkedList<Plugin> installedPlugins = new LinkedList<>();

	public static List<AutomatonFactory> getAvailableAutomatonClasses() {
		List<AutomatonFactory> factories = new LinkedList<>(List.of(
				new CGOLFactory(),
				new KnightCGOLFactory(),
				new Rule110Factory(),
				new WireworldFactory(),
				new BriansBrainFactory(),
				new LangtonsAntFactory()
		));
		factories.addAll(automatonFactoriesFromPlugins);
		return factories;
	}

	public static Collection<ColorPalette> getAvailablePalettes() {
		LinkedList<ColorPalette> palettes = new LinkedList<>();
		for (ColorPalette.DefaultPaletteEntry defaultPaletteEntry : ColorPalette.DefaultPaletteEntry.values()) {
			palettes.add(defaultPaletteEntry.getPalette());
		}
		palettes.addAll(colorPalettesFromPlugins);
		return palettes;
	}

	public static void errorWindow(Exception e) {
		GenericWindow errorWindow = new GenericWindow();
		errorWindow.setTitle(String.format("Error - %s", e.getClass().getSimpleName()));
		errorWindow.getContentPane().add(new JScrollPane(new JLabel(TextUtils.convertToHTML(e.toString()))));
		errorWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		errorWindow.setSize(screen.width / 2, screen.height / 2);
		errorWindow.setLocationRelativeTo(null);
		errorWindow.setVisible(true);
	}

	void main() {
		launch();
	}

	private void launch() {
		//setLookAndFeel();
		PluginLoader.loadAvailablePlugins();
		new AutomatonWindow();
	}

	private void setLookAndFeel() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

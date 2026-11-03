package gui;

import automaton_factories.*;
import engine.AutomatonFactory;
import engine.colors.ColorPalette;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Launcher {

	public static List<AutomatonFactory> getAvailableAutomatonClasses() {
		return List.of(
				new CGOLFactory(),
				new KnightCGOLFactory(),
				new Rule110Factory(),
				new WireworldFactory(),
				new BriansBrainFactory(),
				new LangtonsAntFactory()
		);
	}

	public static Collection<ColorPalette> getAvailablePalettes() {
		LinkedList<ColorPalette> palettes = new LinkedList<>();
		for (ColorPalette.DefaultPaletteEntry defaultPaletteEntry : ColorPalette.DefaultPaletteEntry.values()) {
			palettes.add(defaultPaletteEntry.getPalette());
		}
		return palettes;
	}

	public static void errorWindow(Exception e) {
		GenericWindow errorWindow = new GenericWindow();
		errorWindow.setTitle(String.format("Error - %s", e.getClass().getSimpleName()));
		errorWindow.getContentPane().add(new JScrollPane(new JLabel(String.format("<html>%s</html>", e))));
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
		System.out.println(System.getenv("GDK_SCALE"));
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

package gui;

import automaton_factories.*;
import engine.AutomatonFactory;
import engine.colors.ColorPalette;

import javax.swing.*;
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

	void main() {
		launch();
	}

	private void launch() {
		setLookAndFeel();
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

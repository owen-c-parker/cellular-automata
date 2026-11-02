package gui.menu_bar.palettes;

import engine.colors.ColorPalette;
import gui.AccessorComponent;
import gui.GuiReference;
import gui.Launcher;

import javax.swing.*;
import java.util.Collection;

public class PaletteMenu extends AccessorComponent<JMenu> {
	public PaletteMenu(GuiReference guiReference) {
		super(guiReference, new JMenu("Color Scheme"));

		refreshOptions();
	}

	private void refreshOptions() {
		getComponent().removeAll();
		Collection<ColorPalette> palettes = Launcher.getAvailablePalettes();
		for (ColorPalette palette : palettes) {
			JRadioButtonMenuItem button = new JRadioButtonMenuItem(palette.getDisplayableName());
			if (palette.equals(this.guiReference.getColorPalette()))
				button.setSelected(true);

			button.addActionListener(_ -> {
				this.guiReference.setColorPalette(palette);
				refreshOptions();
			});

			getComponent().add(button);
		}
	}
}

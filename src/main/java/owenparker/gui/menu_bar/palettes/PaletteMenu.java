package owenparker.gui.menu_bar.palettes;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.Launcher;
import owenparker.gui.menu_bar.MenuHeader;
import owenparker.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class PaletteMenu extends AccessorComponent<JMenu> {
	public PaletteMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.COLOR_SCHEME_MENU_OPTION)));

		refreshOptions();
	}

	private void refreshOptions() {
		getComponent().removeAll();

		Launcher.installedPlugins.forEach(this::generateMenuItems);
	}

	private void generateMenuItems(Plugin plugin) {
		getComponent().add(new MenuHeader(this.globalReference, plugin.getDisplayableName()).getComponent());

		plugin.getColorPalettes().forEach(this::generateMenuItem);
	}

	private void generateMenuItem(ColorPalette palette) {
		JRadioButtonMenuItem button = new JRadioButtonMenuItem(palette.getDisplayableName());
		if (palette.equals(this.globalReference.getColorPalette()))
			button.setSelected(true);

		button.addActionListener(_ -> {
			this.globalReference.setColorPalette(palette);
			refreshOptions();
		});

		getComponent().add(button);
	}
}

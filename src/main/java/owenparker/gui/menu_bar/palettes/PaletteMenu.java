package owenparker.gui.menu_bar.palettes;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.menu_bar.PluginResourceLister;
import owenparker.gui.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class PaletteMenu extends AccessorComponent<JMenu> {
	public PaletteMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.COLOR_SCHEME_MENU_OPTION)));

		refreshOptions();
	}

	private void refreshOptions() {
		getComponent().removeAll();

		PluginResourceLister.generateList(
				this,
				this.globalReference::setColorPalette,
				Plugin::getColorPalettes,
				JRadioButtonMenuItem::new
		);
	}
}

package owenparker.gui.menu_bar.language;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.menu_bar.PluginResourceLister;
import owenparker.gui.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class LanguageMenu extends AccessorComponent<JMenu> {
	public LanguageMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.LANGUAGE_MENU_OPTION)));

		PluginResourceLister.generateList(
				this,
				language -> Language.changeLanguage(language),
				Plugin::getLanguages,
				JRadioButtonMenuItem::new
		);
	}
}

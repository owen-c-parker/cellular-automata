package owenparker.gui.menu_bar.language;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.menu_bar.PluginResourceMenuLister;
import owenparker.gui.lang.Language;
import owenparker.plugins.Plugin;

import javax.swing.*;

public class LanguageMenu extends AccessorComponent<JMenu> {
	public LanguageMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.LANGUAGE_MENU_OPTION)));

		PluginResourceMenuLister<Language> lister = new PluginResourceMenuLister<>(Plugin::getLanguages, Language::changeLanguage);
		lister.setButtonGetter(JRadioButtonMenuItem::new);
		lister.listResources(this);
	}
}

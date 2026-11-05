package owenparker.gui.menu_bar.automaton;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.lang.Language;

import javax.swing.*;

public class AutomatonMenu extends AccessorComponent<JMenu> {
	public AutomatonMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.AUTOMATON_MENU_OPTION)));

		getComponent().add(new BorderPolicyMenu(globalReference).getComponent());
	}
}

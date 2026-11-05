package owenparker.gui.menu_bar.automaton;

import owenparker.engine.BorderPolicy;
import owenparker.engine.HasDisplayableName;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.lang.Language;
import owenparker.gui.menu_bar.ResourceMenuLister;

import javax.swing.*;
import java.util.Arrays;

public class BorderPolicyMenu extends AccessorComponent<JMenu> {
	public BorderPolicyMenu(GlobalReference globalReference) {
		super(globalReference, new JMenu(translation(Language.Key.BORDER_POLICY_MENU_OPTION)));

		ResourceMenuLister<BorderPolicy> lister = new ResourceMenuLister<>(
				globalReference::setBorderPolicy,
				Arrays.asList(BorderPolicy.values())
		);

		lister.listResources(this);
	}
}

package owenparker.gui;

import javax.swing.*;

public class AutomatonConfigPanelContainer extends AccessorComponent<JPanel> {
    public AutomatonConfigPanelContainer(GlobalReference globalReference) {
        super(globalReference, new JPanel());

        globalReference.newAutomatonEvent.add(this::refresh);
        refresh();
    }

    private void refresh() {
        getComponent().removeAll();
        getComponent().add(globalReference.getAutomaton().getConfig().getPanel());
    }
}

package owenparker.gui.automaton_config;

import owenparker.config.Configuration;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class AutomatonConfigPanelContainer extends AccessorComponent<JPanel> {
    public AutomatonConfigPanelContainer(GlobalReference globalReference) {
        super(globalReference, new JPanel());

        globalReference.newAutomatonEvent.add(this::refresh);
        refresh();
    }

    private void refresh() {
        getComponent().removeAll();
        Configuration config = globalReference.getAutomaton().getConfig();
        getComponent().add(config.getPanel());
        config.getEntries().forEach(entry -> entry.addSetListener(globalReference.repaintEvent::fire));
    }
}

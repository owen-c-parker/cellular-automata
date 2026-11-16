package owenparker.gui.automaton_config;

import owenparker.config.Configuration;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;

public class AutomatonConfigPanelContainer extends JPanel {
    public AutomatonConfigPanelContainer() {
        super();

        Events.newAutomaton.add(this::refresh);
        refresh();
    }

    private void refresh() {
        removeAll();
        Configuration config = SimulationContext.getAutomaton().getConfig();
        add(config.getPanel());
        config.getEntries().forEach(entry -> entry.addSetListener(Events.repaint::fire));
    }
}

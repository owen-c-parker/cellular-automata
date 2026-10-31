package gui.automaton_display.menu_options;

import engine.AgentFactory;
import gui.automaton_display.DisplayerMenuBar;

import javax.swing.*;
import java.util.List;

public class AgentSelector extends JMenu {
    public AgentSelector() {
        super("Agents");
    }

    public void setPaintButtons(ButtonGroup buttonGroup, List<AgentFactory> selectable, DisplayerMenuBar menuBar) {
        removeAll();
        for (AgentFactory behavior : selectable) {
            JRadioButtonMenuItem button = new JRadioButtonMenuItem(behavior.getDisplayableName());
            button.addActionListener(_ -> menuBar.automatonDisplayer.selectedForPlacing = behavior);
            buttonGroup.add(button);
            add(button);
        }
    }
}

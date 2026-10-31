package gui.automaton_display.menu_options;

import engine.AgentBehavior;
import engine.State;
import gui.automaton_display.AutomatonDisplayer;
import gui.automaton_display.DisplayerMenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgentSelector extends JMenu {
    public AgentSelector() {
        super("Agents");
    }

    public void setPaintButtons(ButtonGroup buttonGroup, List<AgentBehavior> selectable, DisplayerMenuBar menuBar) {
        removeAll();
        for (AgentBehavior behavior : selectable) {
            JRadioButtonMenuItem button = new JRadioButtonMenuItem(behavior.getDisplayableName());
            button.addActionListener(_ -> menuBar.automatonDisplayer.selectedForPlacing = behavior);
            buttonGroup.add(button);
            add(button);
        }
    }
}

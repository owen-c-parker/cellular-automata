package gui.automaton_display;

import engine.AgentBehavior;
import engine.State;
import gui.automaton_display.menu_options.AgentSelector;
import gui.automaton_display.menu_options.FileSelector;
import gui.automaton_display.menu_options.PaintSelector;
import gui.automaton_display.menu_options.ToolSelector;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DisplayerMenuBar extends JMenuBar {
	public final AutomatonDisplayer automatonDisplayer;

	private final PaintSelector paintSelector = new PaintSelector();
    private final AgentSelector agentSelector = new AgentSelector();

	public DisplayerMenuBar(AutomatonDisplayer automatonDisplayer) {
		this.automatonDisplayer = automatonDisplayer;

		this.add(new FileSelector(automatonDisplayer));
		this.add(this.paintSelector);
        this.add(this.agentSelector);
		this.add(new ToolSelector(automatonDisplayer));
	}

	public void setPaintButtons(List<State> selectableStates, List<AgentBehavior> selectableBehaviors) {
        ButtonGroup paintGroup = new ButtonGroup();
		this.paintSelector.setPaintButtons(paintGroup, selectableStates, this);
        this.agentSelector.setPaintButtons(paintGroup, selectableBehaviors, this);
	}
}

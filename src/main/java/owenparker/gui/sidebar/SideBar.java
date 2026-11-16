package owenparker.gui.sidebar;

import owenparker.engine.AgentFactory;
import owenparker.engine.CursorPlaceable;
import owenparker.engine.State;
import owenparker.gui.CursorMode;
import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SideBar extends JScrollPane {
	private List<SideBarButton> buttons;

	public void refreshSelection() {
		this.buttons.forEach(SideBarButton::refreshSelection);
	}

	public SideBar() {
		super();

		Events.newAutomaton.add(this::refresh);
		Events.newColorPalette.add(this::refresh);

		refresh();
	}

	private void refresh() {

		this.buttons = new LinkedList<>();

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

		for (CursorMode cursorMode : CursorMode.values()) {
			SideBarButton button = cursorMode.getButton(this);
			innerPanel.add(button);
			this.buttons.add(button);
			innerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		}

		List<State> states = SimulationContext.getAutomaton().getSelectableCellStates();
		if (!states.isEmpty())
			createButtonsForPlaceables(innerPanel, states, "Cells");

		List<AgentFactory> agents = SimulationContext.getAutomaton().getSelectableAgentFactories();
		if (!agents.isEmpty())
			createButtonsForPlaceables(innerPanel, agents, "Agents");

		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(innerPanel, BorderLayout.NORTH);

		setViewportView(outerPanel);
		setPreferredSize(new Dimension(64, 0));
		getVerticalScrollBar().setUnitIncrement(16);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		refreshSelection();
	}

	private void createButtonsForPlaceables(JComponent target, List<? extends CursorPlaceable> placeables, String title) {
		target.add(new JSeparator(SwingConstants.HORIZONTAL));
		target.add(new JLabel(title));
		for (CursorPlaceable placeable : placeables) {
			createButtonFromPlaceable(target, placeable);
		}
	}

	private void createButtonFromPlaceable(JComponent target, CursorPlaceable placeable) {
		PlaceableButton button = new PlaceableButton(placeable, this);
		target.add(button);
		this.buttons.add(button);
		target.add(Box.createRigidArea(new Dimension(0, 5)));
	}
}

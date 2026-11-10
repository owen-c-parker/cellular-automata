package owenparker.gui.sidebar;

import owenparker.engine.*;
import owenparker.gui.AccessorComponent;
import owenparker.gui.CursorMode;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SideBar extends AccessorComponent<JScrollPane> {
	private List<SideBarButton> buttons;

	public void refreshSelection() {
		this.buttons.forEach(SideBarButton::refreshSelection);
	}

	public SideBar(GlobalReference globalReference) {
		super(globalReference, new JScrollPane());

		globalReference.newAutomatonEvent.add(this::refresh);

		refresh();
	}

	private void refresh() {

		this.buttons = new LinkedList<>();

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

		for (CursorMode cursorMode : CursorMode.values()) {
			SideBarButton button = cursorMode.getButton(this);
			innerPanel.add(button.getComponent());
			this.buttons.add(button);
			innerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		}

		List<State> states = this.globalReference.getAutomaton().getSelectableCellStates();
		if (!states.isEmpty())
			createButtonsForPlaceables(innerPanel, states, "Cells");

		List<AgentFactory> agents = this.globalReference.getAutomaton().getSelectableAgentFactories();
		if (!agents.isEmpty())
			createButtonsForPlaceables(innerPanel, agents, "Agents");

		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(innerPanel, BorderLayout.NORTH);

		getComponent().setViewportView(outerPanel);
		getComponent().setPreferredSize(new Dimension(64, 0));
		getComponent().getVerticalScrollBar().setUnitIncrement(16);
		getComponent().setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

	private void createButtonsForPlaceables(JComponent target, List<? extends CursorPlaceable> placeables, String title) {
		target.add(new JSeparator(SwingConstants.HORIZONTAL));
		target.add(new JLabel(title));
		for (CursorPlaceable placeable : placeables) {
			createButtonFromPlaceable(target, placeable);
		}
	}

	private void createButtonFromPlaceable(JComponent target, CursorPlaceable placeable) {
		PlaceableButton button = new PlaceableButton(this.globalReference, placeable, this);
		target.add(button.getComponent());
		this.buttons.add(button);
		target.add(Box.createRigidArea(new Dimension(0, 5)));
	}
}

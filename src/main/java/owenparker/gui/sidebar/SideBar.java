package owenparker.gui.sidebar;

import owenparker.engine.*;
import owenparker.engine.colors.ColorEvaluator;
import owenparker.engine.colors.HasColor;
import owenparker.engine.colors.StaticColor;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SideBar extends AccessorComponent<JScrollPane> {
	private List<PlaceableButton> placeableButtons;

	public void refreshSelection() {
		this.placeableButtons.forEach(PlaceableButton::refreshSelection);
	}

	public SideBar(GlobalReference globalReference) {
		super(globalReference, new JScrollPane());

		globalReference.newAutomatonEvent.add(this::refresh);

		refresh();
	}

	private void refresh() {
		this.placeableButtons = new LinkedList<>();

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

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
		target.add(new JLabel(title));
		for (CursorPlaceable placeable : placeables) {
			createButtonFromPlaceable(target, placeable);
		}
	}

	private void createButtonFromPlaceable(JComponent target, CursorPlaceable placeable) {
		PlaceableButton button = new PlaceableButton(this.globalReference, placeable, this);
		target.add(button.getComponent());
		this.placeableButtons.add(button);
		target.add(Box.createRigidArea(new Dimension(0, 5)));
	}
}

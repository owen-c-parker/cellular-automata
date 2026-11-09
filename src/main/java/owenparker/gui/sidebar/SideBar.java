package owenparker.gui.sidebar;

import owenparker.engine.Board;
import owenparker.engine.CursorPlaceable;
import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.engine.colors.ColorEvaluator;
import owenparker.engine.colors.HasColor;
import owenparker.engine.colors.StaticColor;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SideBar extends AccessorComponent<JScrollPane> {

	public SideBar(GlobalReference globalReference) {
		super(globalReference, new JScrollPane());

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));



		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(innerPanel, BorderLayout.NORTH);

		getComponent().setViewportView(outerPanel);
		getComponent().setPreferredSize(new Dimension(64, 0));
		getComponent().getVerticalScrollBar().setUnitIncrement(16);
		getComponent().setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

	private void createButtonFromPlaceable(JComponent target, CursorPlaceable placeable) {
		target.add(new PlaceableButton(this.globalReference, placeable).getComponent());
		target.add(Box.createRigidArea(new Dimension(0, 5)));
	}
}

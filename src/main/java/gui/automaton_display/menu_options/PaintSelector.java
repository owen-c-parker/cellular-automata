package gui.automaton_display.menu_options;

import engine.State;
import gui.automaton_display.DisplayerMenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaintSelector extends JMenu {

	public PaintSelector() {
		super("Paint");


	}

	private double getBrightness(Color color) {
		return ((color.getRed() + color.getGreen() + color.getBlue()) / 3.0) / 256.0;
	}

	public void setPaintButtons(ButtonGroup buttonGroup, List<State> selectable, DisplayerMenuBar menuBar) {
		removeAll();
		int index = 0;
		for (State state : selectable) {
			JRadioButtonMenuItem button = new JRadioButtonMenuItem(state.getDisplayableName());
			button.setForeground(getBrightness(state.getColor()) > .5 ? Color.BLACK : Color.WHITE);
			button.setBackground(state.getColor());
			button.addActionListener(_ -> menuBar.automatonDisplayer.selectedForPlacing = state);
            buttonGroup.add(button);
			if (index == 1)
                buttonGroup.setSelected(button.getModel(), true);
			add(button);
			index++;
		}
	}
}

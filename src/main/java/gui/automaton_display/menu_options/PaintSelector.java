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

	public void setPaintButtons(List<State> selectable, DisplayerMenuBar menuBar) {
		removeAll();
		ButtonGroup paintSelectorButtonGroup = new ButtonGroup();
		int index = 0;
		for (State state : selectable) {
			JRadioButtonMenuItem button = new JRadioButtonMenuItem(state.getDisplayableName());
			button.setForeground(getBrightness(state.getColor()) > .5 ? Color.BLACK : Color.WHITE);
			button.setBackground(state.getColor());
			button.addActionListener(_ -> menuBar.automatonDisplayer.currentPaintedState = state);
			paintSelectorButtonGroup.add(button);
			if (index == 1)
				paintSelectorButtonGroup.setSelected(button.getModel(), true);
			add(button);
			index++;
		}
	}
}

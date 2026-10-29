package gui.automatonDisplay.menuOptions;

import engine.State;
import gui.automatonDisplay.DisplayerMenuBar;

import javax.swing.*;
import java.awt.*;

public class PaintSelector extends JMenu {

	public PaintSelector(DisplayerMenuBar menuBar) {
		super("Paint");

		ButtonGroup paintSelectorButtonGroup = new ButtonGroup();
		int index = 0;
		for (State state : menuBar.automatonDisplayer.automaton.getSelectableForPainting()) {
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

	private double getBrightness(Color color) {
		return ((color.getRed() + color.getGreen() + color.getBlue()) / 3.0) / 256.0;
	}
}

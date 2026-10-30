package gui.automaton_display;

import engine.State;
import gui.automaton_display.menu_options.FileSelector;
import gui.automaton_display.menu_options.PaintSelector;
import gui.automaton_display.menu_options.ToolSelector;

import javax.swing.*;
import java.util.List;

public class DisplayerMenuBar extends JMenuBar {
	public final AutomatonDisplayer automatonDisplayer;

	private final PaintSelector paintSelector = new PaintSelector();

	public DisplayerMenuBar(AutomatonDisplayer automatonDisplayer) {
		this.automatonDisplayer = automatonDisplayer;

		this.add(new FileSelector(automatonDisplayer));
		this.add(this.paintSelector);
		this.add(new ToolSelector(automatonDisplayer));
	}

	public void setPaintButtons(List<State> selectable) {
		this.paintSelector.setPaintButtons(selectable, this);
	}
}

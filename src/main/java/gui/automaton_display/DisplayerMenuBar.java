package gui.automaton_display;

import gui.automaton_display.menu_options.PaintSelector;
import gui.automaton_display.menu_options.ToolSelector;

import javax.swing.*;

public class DisplayerMenuBar extends JMenuBar {
	public final AutomatonDisplayer automatonDisplayer;

	public DisplayerMenuBar(AutomatonDisplayer automatonDisplayer) {
		this.automatonDisplayer = automatonDisplayer;

		this.add(new PaintSelector(this));
		this.add(new ToolSelector(automatonDisplayer.automaton, automatonDisplayer));
	}
}

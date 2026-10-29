package gui.automatonDisplay;

import gui.automatonDisplay.menuOptions.PaintSelector;
import gui.automatonDisplay.menuOptions.ToolSelector;

import javax.swing.*;

public class DisplayerMenuBar extends JMenuBar {
	public final AutomatonDisplayer automatonDisplayer;

	public DisplayerMenuBar(AutomatonDisplayer automatonDisplayer) {
		this.automatonDisplayer = automatonDisplayer;

		this.add(new PaintSelector(this));
		this.add(new ToolSelector(automatonDisplayer.automaton, automatonDisplayer));
	}
}

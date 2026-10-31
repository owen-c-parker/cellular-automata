package gui.automaton_display;

import engine.Automaton;
import engine.CursorPlaceable;
import engine.State;
import gui.AutomatonController;
import gui.StatsDisplayer;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomatonDisplayer extends JFrame {
	private final DisplayerMenuBar menuBar;
	private final AutomatonController controller;
	private final StatsDisplayer stats;
	public Automaton automaton;
	private final DisplayerPanel displayerPanel;

	public CursorPlaceable selectedForPlacing;

	final Set<Runnable> drawCallbacks = new HashSet<>();

	public void registerRedrawListener(Runnable callback) {
		this.drawCallbacks.add(callback);
	}

	public AutomatonDisplayer() {

		this.controller = new AutomatonController();
		this.stats = new StatsDisplayer(this);

		this.menuBar = new DisplayerMenuBar(this);

		this.setJMenuBar(this.menuBar);
		this.displayerPanel = new DisplayerPanel(this);
		this.setContentPane(this.displayerPanel);

		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public DisplayerPanel getDisplayerPanel() {
		return this.displayerPanel;
	}

	public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
        List<State> selectableStates = automaton.getSelectableCellStates();
        this.selectedForPlacing = selectableStates.size() > 1 ? selectableStates.get(1) : selectableStates.getFirst();
        setTitle(automaton.getDisplayableName());

        this.menuBar.setPaintButtons(selectableStates, automaton.getSelectableAgentFactories());

        this.controller.setAutomaton(automaton);
        this.stats.setAutomaton(automaton);

        repaint();

        automaton.registerUpdateListener(this::repaint);
	}
}

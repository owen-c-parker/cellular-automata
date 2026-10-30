package gui.automaton_display;

import engine.Automaton;
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

	public State currentPaintedState;

	final Set<Runnable> drawCallbacks = new HashSet<>();

	public void registerRedrawListener(Runnable callback) {
		this.drawCallbacks.add(callback);
	}

	public AutomatonDisplayer() {

		this.controller = new AutomatonController(this);
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
		List<State> selectable = automaton.getSelectableCellStates();
		this.currentPaintedState = selectable.size() > 1 ? selectable.get(1) : selectable.getFirst();
		setTitle(automaton.getDisplayableName());

		this.menuBar.setPaintButtons(selectable);

		this.controller.setAutomaton(automaton);
		this.stats.setAutomaton(automaton);

		repaint();

		automaton.registerUpdateListener(this::repaint);
		this.automaton = automaton;
	}
}

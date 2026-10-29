package gui.automatonDisplay;

import engine.Automaton;
import engine.State;

import java.util.HashSet;
import java.util.List;

import javax.swing.*;
import java.util.Set;

public class AutomatonDisplayer extends JFrame {
	public final Automaton automaton;
	private final DisplayerPanel displayerPanel;

	public State currentPaintedState;

	final Set<Runnable> drawCallbacks = new HashSet<>();

	public void registerRedrawListener(Runnable callback) {
		this.drawCallbacks.add(callback);
	}

	public AutomatonDisplayer(Automaton automaton, int cellScale) {
		this.automaton = automaton;

		List<State> selectable = automaton.getSelectableForPainting();

		this.currentPaintedState = selectable.size() > 1 ? selectable.get(1) : selectable.getFirst();

		this.setJMenuBar(new DisplayerMenuBar(this));
		this.displayerPanel = new DisplayerPanel(this, cellScale);
		this.setContentPane(this.displayerPanel);

		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		repaint();
		automaton.registerUpdateListener(this::repaint);
	}

	public DisplayerPanel getDisplayerPanel() {
		return this.displayerPanel;
	}
}

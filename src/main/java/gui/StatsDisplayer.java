package gui;

import engine.Automaton;
import gui.automatonDisplay.AutomatonDisplayer;

import javax.swing.*;

public class StatsDisplayer extends JFrame {
	private final Automaton automaton;
	private final AutomatonDisplayer displayer;

	public StatsDisplayer(Automaton automaton, AutomatonDisplayer displayer) {
		this.automaton = automaton;
		this.displayer = displayer;

		setContentPane(new StatsPanel());

		setTitle("Stats");

		setSize(100, 200);
		setLocationRelativeTo(null);
		setLocation(10, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class StatsPanel extends JPanel {
		private final JLabel cellCountLabel = new JLabel();

		private final JLabel zoomLevelLabel = new JLabel();

		public StatsPanel() {
			add(this.cellCountLabel);
			add(this.zoomLevelLabel);
			StatsDisplayer.this.automaton.registerUpdateListener(this::updateStats);
			StatsDisplayer.this.displayer.registerRedrawListener(this::updateStats);
			updateStats();
		}
		public void updateStats() {
			int activeCellCount = StatsDisplayer.this.automaton.getActiveCellCount();
			this.cellCountLabel.setText(String.format("Active cells: %d", activeCellCount));
			this.zoomLevelLabel.setText(String.format("Zoom level: %.2f", StatsDisplayer.this.displayer.getDisplayerPanel().getCellScale()));
		}

	}
}

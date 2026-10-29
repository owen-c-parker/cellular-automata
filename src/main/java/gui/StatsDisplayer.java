package gui;

import engine.Automaton;
import engine.State;

import javax.swing.*;

public class StatsDisplayer extends JFrame {
	private final Automaton automaton;

	private class StatsPanel extends JPanel {
		private final JLabel cellCountLabel = new JLabel();

		public StatsPanel() {
			StatsDisplayer.this.automaton.registerUpdateListener(this::updateStats);
			add(this.cellCountLabel);
		}

		public void updateStats() {
			int activeCellCount = StatsDisplayer.this.automaton.getActiveCellCount();
			this.cellCountLabel.setText(String.format("Active cells: %d", activeCellCount));
		}
	}

	public StatsDisplayer(Automaton automaton) {
		this.automaton = automaton;

		setContentPane(new StatsPanel());

		this.setSize(100, 200);
		this.setLocationRelativeTo(null);
		this.setLocation(10, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

package gui.OLD;

import engine.Automaton;
import gui.OLD.automaton_display.AutomatonDisplayer;

import javax.swing.*;

public class StatsDisplayer extends JFrame {
	private Automaton automaton;
	private final AutomatonDisplayer displayer;

	private final StatsPanel statsPanel;

	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
		automaton.registerUpdateListener(this.statsPanel::updateStats);
		this.statsPanel.updateStats();
	}

	public StatsDisplayer(AutomatonDisplayer displayer) {
		this.displayer = displayer;

		this.statsPanel = new StatsPanel();
		setContentPane(this.statsPanel);

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
			StatsDisplayer.this.displayer.registerRedrawListener(this::updateStats);
		}
		public void updateStats() {
			int activeCellCount = StatsDisplayer.this.automaton.getActiveCellCount();
			this.cellCountLabel.setText(String.format("Active cells: %d", activeCellCount));
			this.zoomLevelLabel.setText(String.format("Zoom level: %.2f", StatsDisplayer.this.displayer.getDisplayerPanel().getCellScale()));
		}

	}
}

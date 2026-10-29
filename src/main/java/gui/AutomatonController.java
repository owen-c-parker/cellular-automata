package gui;

import engine.Automaton;
import gui.automaton_display.AutomatonDisplayer;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.util.concurrent.atomic.AtomicInteger;

public class AutomatonController extends JFrame {
	private Automaton automaton;

	private boolean currentlyPlaying = false;

	private double ticksPerSecond = 8;

	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
	}

	public AutomatonController(AutomatonDisplayer displayer) {
		setTitle("Controls");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setContentPane(new AutomatonControllerContent());
		setSize(350, 100);
		setVisible(true);

		AtomicInteger intervalsWaited = new AtomicInteger();
		Timer timer = new Timer(10, _ -> {
			if (intervalsWaited.get() >= 100 / this.ticksPerSecond && this.currentlyPlaying) {
				intervalsWaited.set(0);
				automaton.step();
			}
			intervalsWaited.getAndIncrement();
		});

		timer.setRepeats(true);
		timer.start();
	}

	private class AutomatonControllerContent extends JMenuBar {
		public AutomatonControllerContent() {
			JButton playPauseButton = new JButton("▶");
			playPauseButton.addActionListener(_ -> {
				if (!AutomatonController.this.currentlyPlaying) {
					playPauseButton.setText("⏸");
					AutomatonController.this.currentlyPlaying = true;
				} else {
					playPauseButton.setText("▶");
					AutomatonController.this.currentlyPlaying = false;
				}
			});
			add(playPauseButton);

			JButton stepButton = new JButton("⏯");
			stepButton.addActionListener(_ -> AutomatonController.this.automaton.step());
			add(stepButton);

			JLabel speedLabel = new JLabel("10 steps/s");

			JSlider speedSlider = new JSlider();
			speedSlider.setMinimum(-3);
			speedSlider.setMaximum(20);
			ChangeListener changeListener = _ -> {
				double speedValue = Math.pow(Math.sqrt(2), speedSlider.getValue());
				speedLabel.setText(String.format("%.2f steps/s", speedValue));
				AutomatonController.this.ticksPerSecond = speedValue;
			};
			changeListener.stateChanged(null);
			speedSlider.addChangeListener(changeListener);
			speedSlider.setValue(6);
			speedSlider.setSnapToTicks(true);
			speedSlider.setPaintTicks(true);
			add(speedSlider);
			add(speedLabel);
		}
	}
}

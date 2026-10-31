package gui;

import engine.Automaton;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.util.concurrent.atomic.AtomicReference;

public class AutomatonController extends JFrame {
	private Automaton automaton;

	private boolean currentlyPlaying = false;

	private double ticksPerMillisecond = 8;

	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
	}

	public AutomatonController() {
		setTitle("Controls");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setContentPane(new AutomatonControllerContent());
		setSize(600, 150);
		setVisible(true);

        AtomicReference<Double> queuedTicks = new AtomicReference<>((double) 0);
		Timer timer = new Timer(1, _ -> {
            if (!this.currentlyPlaying)
                return;

            queuedTicks.updateAndGet(v -> v + this.ticksPerMillisecond);
            while (queuedTicks.get() > 1) {
                queuedTicks.updateAndGet(v -> v - 1);
                this.automaton.step();
            }
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
			speedSlider.setMaximum(40);
			ChangeListener changeListener = _ -> {
				double speedValue = Math.pow(Math.sqrt(2), speedSlider.getValue());
				speedLabel.setText(String.format("%.2f steps/s", speedValue));
				AutomatonController.this.ticksPerMillisecond = speedValue / 1000;
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

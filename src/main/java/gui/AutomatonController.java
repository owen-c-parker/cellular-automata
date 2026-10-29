package gui;

import engine.Automaton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.concurrent.atomic.AtomicInteger;

public class AutomatonController extends JFrame {
	private final Automaton automaton;

	private boolean currentlyPlaying = false;

	private double ticksPerSecond = 8;

	public AutomatonController(Automaton automaton) {
		this.automaton = automaton;


		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(400, 100);

		this.setContentPane(new AutomatonControllerContent());
		//setLayout(new BorderLayout());
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
			playPauseButton.addActionListener(e -> {
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

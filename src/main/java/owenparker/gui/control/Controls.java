package owenparker.gui.control;

import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controls extends JPanel {
    public Controls() {
        super();
		refreshControls();
        startTimer();

        Events.newAutomaton.add(this::refreshControls);
    }

    private void refreshControls() {
        removeAll();

        add(new PauseButton());
        add(new StepButton());
        add(new SpeedController());
		add(new WarpButton());

        Events.speedChange.fire();
    }


    private void startTimer() {
        AtomicReference<Double> queuedTicks = new AtomicReference<>((double) 0);

        Timer timer = new Timer(1, _ -> {
            if (!SimulationContext.currentlyPlaying)
                return;

            queuedTicks.updateAndGet(v -> v + SimulationContext.ticksPerMillisecond);
            while (queuedTicks.get() > 1) {
                queuedTicks.updateAndGet(v -> v - 1);
				SimulationContext.step();
            }
        });

        timer.setRepeats(true);
        timer.start();
    }
}

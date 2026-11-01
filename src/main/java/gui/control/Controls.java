package gui.control;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controls extends AccessorComponent<JPanel> {
    public Controls(GuiReference guiReference) {
        super(guiReference);

        setComponent(new JPanel());

        refreshControls();
        startTimer();

        guiReference.newAutomatonEvent.add(this::refreshControls);
    }

    private void refreshControls() {
        getComponent().removeAll();

        getComponent().add(new PauseButton(this.guiReference).getComponent());
        getComponent().add(new StepButton(this.guiReference).getComponent());
        getComponent().add(new SpeedController(this.guiReference).getComponent());

        this.guiReference.speedChangeEvent.fire();
    }


    private void startTimer() {
        AtomicReference<Double> queuedTicks = new AtomicReference<>((double) 0);

        Timer timer = new Timer(1, _ -> {
            if (!this.guiReference.currentlyPlaying)
                return;

            queuedTicks.updateAndGet(v -> v + this.guiReference.ticksPerMillisecond);
            while (queuedTicks.get() > 1) {
                queuedTicks.updateAndGet(v -> v - 1);
                this.guiReference.step();
            }
        });

        timer.setRepeats(true);
        timer.start();
    }
}

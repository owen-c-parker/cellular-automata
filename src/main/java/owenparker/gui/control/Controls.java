package owenparker.gui.control;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controls extends AccessorComponent<JPanel> {
    public Controls(GlobalReference globalReference) {
        super(globalReference);

        setComponent(new JPanel());

        refreshControls();
        startTimer();

        globalReference.newAutomatonEvent.add(this::refreshControls);
    }

    private void refreshControls() {
        getComponent().removeAll();

        getComponent().add(new PauseButton(this.globalReference).getComponent());
        getComponent().add(new StepButton(this.globalReference).getComponent());
        getComponent().add(new SpeedController(this.globalReference).getComponent());
		getComponent().add(new WarpButton(this.globalReference).getComponent());

        this.globalReference.speedChangeEvent.fire();
    }


    private void startTimer() {
        AtomicReference<Double> queuedTicks = new AtomicReference<>((double) 0);

        Timer timer = new Timer(1, _ -> {
            if (!this.globalReference.currentlyPlaying)
                return;

            queuedTicks.updateAndGet(v -> v + this.globalReference.ticksPerMillisecond);
            while (queuedTicks.get() > 1) {
                queuedTicks.updateAndGet(v -> v - 1);
                this.globalReference.step();
            }
        });

        timer.setRepeats(true);
        timer.start();
    }
}

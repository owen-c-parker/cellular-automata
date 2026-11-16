package owenparker.gui.control;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;

public class PauseButton extends JButton {
    public PauseButton() {
        super();

        pause();

        addActionListener(_ -> {
            if (SimulationContext.currentlyPlaying)
                pause();
            else
                play();
        });
    }

    private void pause() {
        setText("▶");
        setToolTipText("Start the simulation");
		SimulationContext.currentlyPlaying = false;
    }

    private void play() {
        setText("⏸");
        setToolTipText("Freeze the simulation");
		SimulationContext.currentlyPlaying = true;
    }
}

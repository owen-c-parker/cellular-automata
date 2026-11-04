package owenparker.gui.control;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class PauseButton extends AccessorComponent<JButton> {
    public PauseButton(GlobalReference globalReference) {
        super(globalReference, new JButton());

        pause();

        getComponent().addActionListener(_ -> {
            if (globalReference.currentlyPlaying)
                pause();
            else
                play();
        });
    }

    private void pause() {
        getComponent().setText("▶");
        getComponent().setToolTipText("Start the simulation");
        this.globalReference.currentlyPlaying = false;
    }

    private void play() {
        getComponent().setText("⏸");
        getComponent().setToolTipText("Freeze the simulation");
        this.globalReference.currentlyPlaying = true;
    }
}

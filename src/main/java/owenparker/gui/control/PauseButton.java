package owenparker.gui.control;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

import javax.swing.*;

public class PauseButton extends AccessorComponent<JButton> {
    public PauseButton(GuiReference guiReference) {
        super(guiReference, new JButton());

        pause();

        getComponent().addActionListener(_ -> {
            if (guiReference.currentlyPlaying)
                pause();
            else
                play();
        });
    }

    private void pause() {
        getComponent().setText("▶");
        getComponent().setToolTipText("Start the simulation");
        this.guiReference.currentlyPlaying = false;
    }

    private void play() {
        getComponent().setText("⏸");
        getComponent().setToolTipText("Freeze the simulation");
        this.guiReference.currentlyPlaying = true;
    }
}

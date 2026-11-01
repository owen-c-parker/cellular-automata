package gui.control;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;

public class StepButton extends AccessorComponent<JButton> {
    public StepButton(GuiReference guiReference) {
        super(guiReference, new JButton("⏯"));
        getComponent().addActionListener(_ -> guiReference.step());
        getComponent().setToolTipText("Step once");
    }
}

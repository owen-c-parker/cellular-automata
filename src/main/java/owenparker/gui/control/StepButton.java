package owenparker.gui.control;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

import javax.swing.*;

public class StepButton extends AccessorComponent<JButton> {
    public StepButton(GuiReference guiReference) {
        super(guiReference, new JButton("⏯"));
        getComponent().addActionListener(_ -> guiReference.step());
        getComponent().setToolTipText("Step once");
    }
}

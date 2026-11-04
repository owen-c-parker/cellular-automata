package owenparker.gui.control;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;

public class StepButton extends AccessorComponent<JButton> {
    public StepButton(GlobalReference globalReference) {
        super(globalReference, new JButton("⏯"));
        getComponent().addActionListener(_ -> globalReference.step());
        getComponent().setToolTipText("Step once");
    }
}

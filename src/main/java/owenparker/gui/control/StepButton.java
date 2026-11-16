package owenparker.gui.control;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;

public class StepButton extends JButton {
    public StepButton() {
        super("⏯");
        addActionListener(_ -> SimulationContext.step());
		setToolTipText("Step once");
    }
}

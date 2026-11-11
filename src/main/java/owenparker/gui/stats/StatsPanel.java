package owenparker.gui.stats;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.TextUtils;

import javax.swing.*;
import java.util.Objects;

public class StatsPanel extends AccessorComponent<JPanel> {
    public StatsPanel(GlobalReference globalReference) {
        super(globalReference, new JPanel());

		getComponent().setLayout(new BoxLayout(getComponent(), BoxLayout.Y_AXIS));

        globalReference.repaintEvent.add(this::update);
		globalReference.mouseMoveEvent.add(this::update);
        update();
    }

    private void update() {
		getComponent().removeAll();

		addTextLabelForValue("Cursor location", this.globalReference.gridDisplay.getCursorLocationInCellSpace());
		addTextLabelForValue("Total cells", this.globalReference.getAutomaton().getActiveCellCount());
		addTextLabelForValue("Cells to update", this.globalReference.getAutomaton().getNumberOfCellsThatMightMove());
		addTextLabelForValue("Total steps", this.globalReference.getAutomaton().getTotalSteps());
		addTextLabelForValue("Zoom level", String.format("%.2f", this.globalReference.gridDisplay.getCellScale()));

		getComponent().repaint();
    }

	private void addTextLabelForValue(String label, Object value) {
		getComponent().add(new JLabel(String.format("%s: %s", label, Objects.requireNonNullElse(value, ""))));
	}
}

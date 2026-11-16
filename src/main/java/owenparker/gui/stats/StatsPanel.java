package owenparker.gui.stats;

import owenparker.gui.context.DisplayContext;
import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;

import javax.swing.*;
import java.util.Objects;

public class StatsPanel extends JPanel {
    public StatsPanel() {
		super();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Events.repaint.add(this::update);
		Events.mouseMove.add(this::update);
        update();
    }

    private void update() {
		removeAll();

		addTextLabelForValue("Cursor location", DisplayContext.gridDisplay.getCursorLocationInCellSpace());
		addTextLabelForValue("Total cells", SimulationContext.getAutomaton().getActiveCellCount());
		addTextLabelForValue("Cells to update", SimulationContext.getAutomaton().getNumberOfCellsThatMightMove());
		addTextLabelForValue("Total steps", SimulationContext.getAutomaton().getTotalSteps());
		addTextLabelForValue("Zoom level", String.format("%.2f", DisplayContext.gridDisplay.getCellScale()));

		repaint();
    }

	private void addTextLabelForValue(String label, Object value) {
		add(new JLabel(String.format("%s: %s", label, Objects.requireNonNullElse(value, ""))));
	}
}

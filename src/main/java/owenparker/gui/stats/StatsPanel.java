package owenparker.gui.stats;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.TextUtils;

import javax.swing.*;

public class StatsPanel extends AccessorComponent<JPanel> {
    private final JLabel textLabel = new JLabel();

    public StatsPanel(GlobalReference globalReference) {
        super(globalReference, new JPanel());

        globalReference.repaintEvent.add(this::update);
        getComponent().add(this.textLabel);
        update();
    }

    private void update() {
        this.textLabel.setText(TextUtils.convertToHTML(
                "Total cells: " +
                this.globalReference.getAutomaton().getActiveCellCount() + "\n" +
                "Cells to update: " +
                this.globalReference.getAutomaton().getNumberOfCellsThatMightMove() + "\n" +
                "Zoom level: " +
                String.format("%.2f", this.globalReference.gridDisplay.getCellScale())
        ));
    }
}

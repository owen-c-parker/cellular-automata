package owenparker.gui.stats;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;
import owenparker.gui.TextUtils;

import javax.swing.*;

public class StatsPanel extends AccessorComponent<JPanel> {
    private final JLabel textLabel = new JLabel();

    public StatsPanel(GuiReference guiReference) {
        super(guiReference, new JPanel());

        guiReference.repaintEvent.add(this::update);
        getComponent().add(this.textLabel);
        update();
    }

    private void update() {
        this.textLabel.setText(TextUtils.convertToHTML(String.format(
                "Active cells: %d\nZoom level: %.2f",
                this.guiReference.getAutomaton().getActiveCellCount(),
                this.guiReference.gridDisplay.getCellScale()
        )));
    }
}

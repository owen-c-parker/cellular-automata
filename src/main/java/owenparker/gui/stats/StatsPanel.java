package owenparker.gui.stats;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

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
        this.textLabel.setText(String.format(
                "<html>Active cells: %d<br>Zoom level: %.2f</html>",
                this.guiReference.getAutomaton().getActiveCellCount(),
                this.guiReference.gridDisplay.getCellScale()
        ));
    }
}

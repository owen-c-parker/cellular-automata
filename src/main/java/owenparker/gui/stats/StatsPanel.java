package owenparker.gui.stats;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.TextUtils;
import owenparker.lang.Language;

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
				translation(Language.Key.STAT_ACTIVE_CELLS) +
                this.globalReference.getAutomaton().getActiveCellCount() + "\n" +
				translation(Language.Key.STAT_ZOOM) +
                String.format("%.2f", this.globalReference.gridDisplay.getCellScale())
        ));
    }
}

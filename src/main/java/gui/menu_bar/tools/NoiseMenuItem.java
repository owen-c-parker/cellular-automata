package gui.menu_bar.tools;

import engine.OrderedPair;
import engine.State;
import gui.GuiReference;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class NoiseMenuItem extends ToolMenuItem {
    public NoiseMenuItem(GuiReference guiReference) {
        super(guiReference, "Fill screen with noise", () -> {
            Random random = new Random();
            for (OrderedPair location : guiReference.gridDisplay.getCellLocationsOnScreen()) {
                List<State> selectable = guiReference.getAutomaton().getSelectableCellStates();
                State randomState = selectable.get(random.nextInt(selectable.size()));
                guiReference.getAutomaton().setCellAt(location, randomState);
            }
        });
    }
}

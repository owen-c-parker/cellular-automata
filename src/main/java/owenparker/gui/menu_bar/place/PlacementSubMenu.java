package owenparker.gui.menu_bar.place;

import owenparker.engine.CursorPlaceable;
import owenparker.engine.State;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public abstract class PlacementSubMenu extends AccessorComponent<JMenu> {
    private final ButtonGroup selectedButtonGroup;

    public PlacementSubMenu(GlobalReference globalReference, String label, ButtonGroup selectedButtonGroup) {
        super(globalReference, new JMenu(label));
        this.selectedButtonGroup = selectedButtonGroup;
        addMenuItems();
    }

    private void addMenuItems() {
        for (CursorPlaceable placeable : getPlaceables()) {
            addMenuItem(placeable);
        }
    }

    abstract Collection<CursorPlaceable> getPlaceables();

    private void addMenuItem(CursorPlaceable placeable) {
        JMenuItem menuItem = new JRadioButtonMenuItem(placeable.getDisplayableName());
		getComponent().add(menuItem);

        if (placeable instanceof State state)
            colorMenuItem(menuItem, state);

        menuItem.addActionListener(_ -> this.globalReference.selectedForPlacement = placeable);
        this.selectedButtonGroup.add(menuItem);
        if (placeable.equals(this.globalReference.selectedForPlacement))
            this.selectedButtonGroup.setSelected(menuItem.getModel(), true);
    }

    private void colorMenuItem(JMenuItem menuItem, State state) {
        Color color = state.getColorEvaluator().getColor(this.globalReference.getColorPalette());
        int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        menuItem.setBackground(color);
        menuItem.setForeground(brightness > 128 ? Color.DARK_GRAY : Color.LIGHT_GRAY);
		menuItem.setOpaque(true);
    }
}

package gui.menu_bar.place;

import engine.CursorPlaceable;
import engine.State;
import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public abstract class PlacementSubMenu extends AccessorComponent<JMenu> {
    private final ButtonGroup selectedButtonGroup;

    public PlacementSubMenu(GuiReference guiReference, String label, ButtonGroup selectedButtonGroup) {
        super(guiReference, new JMenu(label));
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

        menuItem.addActionListener(_ -> this.guiReference.selectedForPlacement = placeable);
        this.selectedButtonGroup.add(menuItem);
        if (placeable.equals(this.guiReference.selectedForPlacement))
            this.selectedButtonGroup.setSelected(menuItem.getModel(), true);
    }

    private void colorMenuItem(JMenuItem menuItem, State state) {
        Color color = state.getColorEvaluator().getColor(this.guiReference.getColorPalette());
        int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        menuItem.setBackground(color);
        menuItem.setForeground(brightness > 128 ? Color.DARK_GRAY : Color.LIGHT_GRAY);
		menuItem.setOpaque(true);
    }
}

package owenparker.gui.menu_bar.place;

import owenparker.engine.CursorPlaceable;
import owenparker.engine.State;
import owenparker.engine.colors.HasColor;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.menu_bar.ResourceMenuLister;

import javax.swing.*;
import javax.swing.plaf.ColorChooserUI;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class PlacementSubMenu extends AccessorComponent<JMenu> {
    private final ButtonGroup selectedButtonGroup;

    public PlacementSubMenu(GlobalReference globalReference, String label, ButtonGroup selectedButtonGroup) {
        super(globalReference, new JMenu(label));
        this.selectedButtonGroup = selectedButtonGroup;

		ResourceMenuLister<CursorPlaceable> lister = new ResourceMenuLister<>(
				placeable -> this.globalReference.selectedForPlacement = placeable,
				new LinkedList<>(getPlaceables())
		);

		lister.setButtonGetter(JRadioButtonMenuItem::new);
		lister.setComponentModifier(this::modifyComponent);
		lister.listResources(this);
    }

    abstract Collection<CursorPlaceable> getPlaceables();

	private void modifyComponent(AbstractButton menuItem, CursorPlaceable cursorPlaceable) {
		colorMenuItem(menuItem, cursorPlaceable);
		this.selectedButtonGroup.add(menuItem);
		if (this.globalReference.selectedForPlacement.equals(cursorPlaceable))
			this.selectedButtonGroup.setSelected(menuItem.getModel(), true);
	}

    private void colorMenuItem(AbstractButton menuItem, CursorPlaceable cursorPlaceable) {
        if (!(cursorPlaceable instanceof HasColor hasColor))
			return;

		Color color = hasColor.getColorEvaluator().getColor(this.globalReference.getColorPalette());

        menuItem.setBackground(color);
        menuItem.setForeground(isTooLight(color) ? Color.DARK_GRAY : Color.LIGHT_GRAY);
		menuItem.setOpaque(true);
    }

	private boolean isTooLight(Color color) {
		return getBrightness(color) > 128;
	}

	private int getBrightness(Color color) {
		return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
	}
}

package owenparker.gui.menu_bar.place;

import owenparker.engine.CursorPlaceable;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class PlaceMenu extends AccessorComponent<JMenu> {
    public PlaceMenu(GuiReference guiReference) {
        super(guiReference, new JMenu("Place"));

        guiReference.newAutomatonEvent.add(this::refreshSubMenus);
        refreshSubMenus();
    }

    private void refreshSubMenus() {
        getComponent().removeAll();
        ButtonGroup selectedButtonGroup = new ButtonGroup();
        addSubMenu("Cell", selectedButtonGroup, () -> Collections.unmodifiableList(this.guiReference.getAutomaton().getSelectableCellStates()));
        addSubMenu("Agent", selectedButtonGroup, () -> Collections.unmodifiableList(this.guiReference.getAutomaton().getSelectableAgentFactories()));
    }

    private void addSubMenu(String label, ButtonGroup selectedButtonGroup, Callable<List<CursorPlaceable>> getSelectables) {
        try {
            if (!getSelectables.call().isEmpty())
                getComponent().add(new PlacementSubMenu(this.guiReference, label, selectedButtonGroup) {
                    @Override
                    Collection<CursorPlaceable> getPlaceables() {
                        try {
                            return Collections.unmodifiableCollection(getSelectables.call());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }.getComponent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

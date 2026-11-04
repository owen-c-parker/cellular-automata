package owenparker.gui.menu_bar.place;

import owenparker.engine.CursorPlaceable;
import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.lang.Language;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class PlaceMenu extends AccessorComponent<JMenu> {
    public PlaceMenu(GlobalReference globalReference) {
        super(globalReference, new JMenu(translation(Language.Key.PLACEMENT_MENU_OPTION)));

        globalReference.newAutomatonEvent.add(this::refreshSubMenus);
        refreshSubMenus();
    }

    private void refreshSubMenus() {
        getComponent().removeAll();
        ButtonGroup selectedButtonGroup = new ButtonGroup();
        addSubMenu(translation(Language.Key.CELL_MENU_OPTION), selectedButtonGroup, () -> Collections.unmodifiableList(this.globalReference.getAutomaton().getSelectableCellStates()));
        addSubMenu(translation(Language.Key.AGENT_MENU_OPTION), selectedButtonGroup, () -> Collections.unmodifiableList(this.globalReference.getAutomaton().getSelectableAgentFactories()));
    }

    private void addSubMenu(String label, ButtonGroup selectedButtonGroup, Callable<List<CursorPlaceable>> getSelectables) {
        try {
            if (!getSelectables.call().isEmpty())
                getComponent().add(new PlacementSubMenu(this.globalReference, label, selectedButtonGroup) {
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

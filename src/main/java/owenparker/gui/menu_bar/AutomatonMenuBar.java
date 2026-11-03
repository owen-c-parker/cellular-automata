package owenparker.gui.menu_bar;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GuiReference;
import owenparker.gui.menu_bar.about.AboutMenu;
import owenparker.gui.menu_bar.file.FileMenu;
import owenparker.gui.menu_bar.palettes.PaletteMenu;
import owenparker.gui.menu_bar.place.PlaceMenu;
import owenparker.gui.menu_bar.tools.ToolsMenu;

import javax.swing.*;

public class AutomatonMenuBar extends AccessorComponent<JMenuBar> {

    public AutomatonMenuBar(GuiReference guiReference) {
        super(guiReference, new JMenuBar());

        getComponent().add(new FileMenu(guiReference).getComponent());
        getComponent().add(new PlaceMenu(guiReference).getComponent());
        getComponent().add(new ToolsMenu(guiReference).getComponent());
		getComponent().add(new PaletteMenu(guiReference).getComponent());
		getComponent().add(new AboutMenu(guiReference).getComponent());
    }
}

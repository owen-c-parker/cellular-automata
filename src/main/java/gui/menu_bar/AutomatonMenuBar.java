package gui.menu_bar;

import gui.AccessorComponent;
import gui.GuiReference;
import gui.menu_bar.about.AboutMenu;
import gui.menu_bar.file.FileMenu;
import gui.menu_bar.palettes.PaletteMenu;
import gui.menu_bar.place.PlaceMenu;
import gui.menu_bar.tools.ToolsMenu;

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

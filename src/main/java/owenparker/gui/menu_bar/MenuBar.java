package owenparker.gui.menu_bar;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;
import owenparker.gui.menu_bar.about.AboutMenu;
import owenparker.gui.menu_bar.file.FileMenu;
import owenparker.gui.menu_bar.language.LanguageMenu;
import owenparker.gui.menu_bar.place.PlaceMenu;
import owenparker.gui.menu_bar.tools.ToolsMenu;

import javax.swing.*;

public class MenuBar extends AccessorComponent<JMenuBar> {

    public MenuBar(GlobalReference globalReference) {
        super(globalReference, new JMenuBar());

        getComponent().add(new FileMenu(globalReference).getComponent());
        getComponent().add(new PlaceMenu(globalReference).getComponent());
        getComponent().add(new ToolsMenu(globalReference).getComponent());
		getComponent().add(new AboutMenu(globalReference).getComponent());
		getComponent().add(new LanguageMenu(globalReference).getComponent());
    }
}

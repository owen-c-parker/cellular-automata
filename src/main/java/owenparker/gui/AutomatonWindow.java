package owenparker.gui;

import owenparker.gui.control.Controls;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.menu_bar.MenuBar;
import owenparker.gui.stats.StatsPanel;
import owenparker.gui.lang.Language;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.nio.file.Paths;

public class AutomatonWindow extends GenericWindow {

	public AutomatonWindow() {
        GlobalReference globalReference = new GlobalReference();


        globalReference.newColorPaletteEvent.add(() -> SwingUtilities.updateComponentTreeUI(this));

        globalReference.newAutomatonEvent.add(() -> setTitle(String.format("%s - %s", Language.getCurrentLanguage().get(Language.Key.TITLE), globalReference.getAutomaton().getDisplayableName())));

        globalReference.setAutomaton(Launcher.installedPlugins.getFirst().getAutomatonFactories().getFirst().buildAutomaton());

        setContentPane(new JPanel(new BorderLayout()));

        Controls controls = new Controls(globalReference);
        controls.getComponent().setPreferredSize(new Dimension(0, 50));
        getContentPane().add(controls.getComponent(), BorderLayout.SOUTH);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(new GridDisplay(globalReference).getComponent(), BorderLayout.CENTER);
        topContainer.add(new StatsPanel(globalReference).getComponent(), BorderLayout.EAST);

        getContentPane().add(topContainer, BorderLayout.CENTER);

        setJMenuBar(new MenuBar(globalReference).getComponent());
        setExtendedState(MAXIMIZED_BOTH);

        globalReference.preferences.setEntriesBasedOnConfigFile(FileManagement.getConfigFile());

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

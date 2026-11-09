package owenparker.gui;

import owenparker.gui.automaton_config.AutomatonConfigPanelContainer;
import owenparker.gui.control.Controls;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.menu_bar.MenuBar;
import owenparker.gui.stats.StatsPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends GenericWindow {

	public MainWindow() {
        GlobalReference globalReference = new GlobalReference();


        globalReference.newColorPaletteEvent.add(() -> SwingUtilities.updateComponentTreeUI(this));

        globalReference.newAutomatonEvent.add(() -> setTitle(String.format("Cellular Automaton Simulator - %s", globalReference.getAutomaton().getDisplayableName())));

        globalReference.setAutomaton(Launcher.installedPlugins.getFirst().getAutomatonFactories().getFirst().buildAutomaton());

        setContentPane(new JPanel(new BorderLayout()));

        Controls controls = new Controls(globalReference);
        controls.getComponent().setPreferredSize(new Dimension(0, 50));
        getContentPane().add(controls.getComponent(), BorderLayout.SOUTH);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(new GridDisplay(globalReference).getComponent(), BorderLayout.CENTER);

        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.add(new StatsPanel(globalReference).getComponent(), BorderLayout.SOUTH);
        rightContainer.add(new AutomatonConfigPanelContainer(globalReference).getComponent(), BorderLayout.CENTER);

        topContainer.add(rightContainer, BorderLayout.EAST);

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

package owenparker.gui;

import owenparker.gui.automaton_config.AutomatonConfigPanelContainer;
import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;
import owenparker.gui.control.Controls;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.menu_bar.MenuBar;
import owenparker.gui.preferences.Preferences;
import owenparker.gui.sidebar.SideBar;
import owenparker.gui.stats.StatsPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends GenericWindow {

	public MainWindow() {
        Events.newColorPalette.add(() -> SwingUtilities.updateComponentTreeUI(this));
		Events.newAutomaton.add(() -> setTitle(String.format("Cellular Automaton Simulator - %s", SimulationContext.getAutomaton().getDisplayableName())));

		SimulationContext.buildAutomatonFromFactory(Launcher.installedPlugins.getFirst().getAutomatonFactories().getFirst());

        setContentPane(new JPanel(new BorderLayout()));

        Controls controls = new Controls();
        controls.setPreferredSize(new Dimension(0, 50));
        getContentPane().add(controls, BorderLayout.SOUTH);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(new GridDisplay(), BorderLayout.CENTER);

        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.add(new StatsPanel(), BorderLayout.SOUTH);
        rightContainer.add(new AutomatonConfigPanelContainer(), BorderLayout.CENTER);

        topContainer.add(rightContainer, BorderLayout.EAST);

        getContentPane().add(topContainer, BorderLayout.CENTER);

		getContentPane().add(new SideBar(), BorderLayout.WEST);

        setJMenuBar(new MenuBar());
        setExtendedState(MAXIMIZED_BOTH);

        Preferences.getInstance().setEntriesBasedOnConfigFile(FileManagement.getConfigFile());

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

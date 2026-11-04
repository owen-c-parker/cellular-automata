package owenparker.gui;

import owenparker.gui.control.Controls;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.menu_bar.AutomatonMenuBar;
import owenparker.gui.stats.StatsPanel;
import owenparker.lang.Language;

import javax.swing.*;
import java.awt.*;

public class AutomatonWindow extends GenericWindow {

	public AutomatonWindow() {
        GlobalReference globalReference = new GlobalReference();

		globalReference.setColorPalette(Launcher.installedPlugins.getFirst().getColorPalettes().getFirst());

		globalReference.newColorPaletteEvent.add(() -> SwingUtilities.updateComponentTreeUI(this));

		globalReference.newAutomatonEvent.add(() -> setTitle(String.format("%s - %s", Language.currentLanguage.get(Language.Key.TITLE), globalReference.getAutomaton().getDisplayableName())));

		globalReference.setAutomaton(Launcher.installedPlugins.getFirst().getAutomatonFactories().getFirst().buildAutomaton());

        setContentPane(new JPanel(new BorderLayout()));

        Controls controls = new Controls(globalReference);
        controls.getComponent().setPreferredSize(new Dimension(0, 50));
        getContentPane().add(controls.getComponent(), BorderLayout.SOUTH);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(new GridDisplay(globalReference).getComponent(), BorderLayout.CENTER);
        topContainer.add(new StatsPanel(globalReference).getComponent(), BorderLayout.EAST);

        getContentPane().add(topContainer, BorderLayout.CENTER);

        setJMenuBar(new AutomatonMenuBar(globalReference).getComponent());
        setExtendedState(MAXIMIZED_BOTH);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

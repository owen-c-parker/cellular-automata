package owenparker.gui;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.control.Controls;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.menu_bar.AutomatonMenuBar;
import owenparker.gui.stats.StatsPanel;

import javax.swing.*;
import java.awt.*;

public class AutomatonWindow extends GenericWindow {

	public AutomatonWindow() {
        GuiReference guiReference = new GuiReference();

		guiReference.setColorPalette(ColorPalette.DefaultPaletteEntry.DARK_MODE.getPalette());

		guiReference.newColorPaletteEvent.add(() -> SwingUtilities.updateComponentTreeUI(this));

		guiReference.newAutomatonEvent.add(() -> setTitle(String.format("Automaton Simulator - %s", guiReference.getAutomaton().getDisplayableName())));

		guiReference.setAutomaton(Launcher.getAvailableAutomatonClasses().getFirst().buildAutomaton());

        setContentPane(new JPanel(new BorderLayout()));

        Controls controls = new Controls(guiReference);
        controls.getComponent().setPreferredSize(new Dimension(0, 50));
        getContentPane().add(controls.getComponent(), BorderLayout.SOUTH);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(new GridDisplay(guiReference).getComponent(), BorderLayout.CENTER);
        topContainer.add(new StatsPanel(guiReference).getComponent(), BorderLayout.EAST);

        getContentPane().add(topContainer, BorderLayout.CENTER);

        setJMenuBar(new AutomatonMenuBar(guiReference).getComponent());
        setExtendedState(MAXIMIZED_BOTH);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package gui;

import automata.CGOL;
import gui.OLD.StatsDisplayer;
import gui.control.Controls;
import gui.grid.GridDisplay;
import gui.menu_bar.AutomatonMenuBar;
import gui.stats.StatsPanel;

import javax.swing.*;
import java.awt.*;

public class AutomatonWindow extends JFrame {
    public AutomatonWindow() {
        GuiReference guiReference = new GuiReference();
        guiReference.setAutomaton(new CGOL());

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

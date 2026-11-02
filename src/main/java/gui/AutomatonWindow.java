package gui;

import engine.colors.ColorPalette;
import gui.control.Controls;
import gui.grid.GridDisplay;
import gui.menu_bar.AutomatonMenuBar;
import gui.stats.StatsPanel;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;

public class AutomatonWindow extends JFrame {
	private final static LinkedList<Image> ICONS = new LinkedList<>();

	private static Image getImage(String path) throws IOException {
		URL url = AutomatonWindow.class.getResource(path);
		if (url == null)
			throw new FileNotFoundException();
		return ImageIO.read(url);
	}

	static {
		for (String path : new String[] {"/icon64.png", "/icon32.png", "/icon16.png"}) {
			try {
				ICONS.add(getImage(path));
			} catch (IOException e) {
				System.err.printf("Couldn't find image %s\n", path);
			}
		}
	}

	public AutomatonWindow() {
        GuiReference guiReference = new GuiReference();

		setIconImages(ICONS);

		guiReference.setColorPalette(ColorPalette.DefaultPaletteEntry.DARK_MODE.getPalette());

		guiReference.newColorPaletteEvent.add(() -> SwingUtilities.updateComponentTreeUI(this));

		guiReference.newAutomatonEvent.add(() -> {
			setTitle(String.format("Automaton Simulator - %s", guiReference.getAutomaton().getDisplayableName()));
		});

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

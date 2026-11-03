package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class GenericWindow extends JFrame {
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

	public GenericWindow() {
		setIconImages(ICONS);
	}
}

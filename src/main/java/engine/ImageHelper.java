package engine;

import gui.AutomatonWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class ImageHelper {
	public static void drawImageToFillGraphicsContext(Graphics2D g, Image image, double angle) {
		Rectangle bounds = g.getClipBounds();
		g.rotate(angle, (double) bounds.width / 2, (double) bounds.height / 2);
		g.drawImage(image, 0, 0, bounds.width, bounds.height, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}

	public static void drawImageToFillGraphicsContext(Graphics2D g, Image image) {
		drawImageToFillGraphicsContext(g, image, 0);
	}

	public static Image readImage(String path) {
		try {
			URL url = AutomatonWindow.class.getClassLoader().getResource(path);
			if (url == null)
				throw new FileNotFoundException();
			return ImageIO.read(url);
		} catch (IOException e) {
			System.err.printf("Could not read image %s\n%s\n", path, e);
		}
		return null;
	}
}

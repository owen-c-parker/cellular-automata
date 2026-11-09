package owenparker.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ImageHelper {
	public static void drawImageToFillGraphicsContext(Graphics2D g, Image image, double angle) {
		Rectangle bounds = g.getClipBounds();
		g.rotate(angle, (double) bounds.width / 2, (double) bounds.height / 2);
		g.drawImage(image, 0, 0, bounds.width, bounds.height, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}

	public static void drawImageToFillGraphicsContext(Graphics2D g, Image image) {
		drawImageToFillGraphicsContext(g, image, 0);
	}

	public static Image readImageFromResources(String path) {
		try (InputStream stream = ImageHelper.class.getClassLoader().getResourceAsStream(path)) {
			return ImageIO.read(Objects.requireNonNull(stream));
		} catch (IOException | NullPointerException e) {
			System.err.println("Couldn't read " + path);
			throw new RuntimeException(e);
		}
	}

	public static Image readImage(Path path) {
		return readImage(path.toString());
	}

	public static Image readImage(String path) {
		try {
			return ImageIO.read(Path.of(path).toFile());
		} catch (IOException e) {
			System.err.printf("Could not read image %s\n%s\n", path, e);
		}
		return null;
	}
}

package owenparker.gui;

import owenparker.engine.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class GenericWindow extends JFrame {
	private final static List<Image> ICONS = ImageHelper.readImages("images/icons");

	public GenericWindow() {
		setIconImages(ICONS);
	}
}

package owenparker.gui;

import owenparker.engine.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GenericWindow extends JFrame {
	private final static LinkedList<Image> ICONS = new LinkedList<>();

	static {
		for (String path : new String[] {"/images/icon64.png", "/images/icon32.png", "/images/icon16.png"}) {
			Image image = ImageHelper.readImage(path);
			if (image != null)
				ICONS.add(image);
		}
	}

	public GenericWindow() {
		setIconImages(ICONS);
	}
}

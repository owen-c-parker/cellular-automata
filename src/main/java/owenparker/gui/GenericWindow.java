package owenparker.gui;

import owenparker.engine.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GenericWindow extends JFrame {
	private final static List<Image> ICONS = ImageHelper.readImages("images/icons");

	public GenericWindow() {
		setIconImages(ICONS);
	}
}

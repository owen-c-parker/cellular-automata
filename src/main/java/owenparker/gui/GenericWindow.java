package owenparker.gui;

import owenparker.engine.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

public class GenericWindow extends JFrame {
	private final static List<Image> ICONS = Stream.of(
			16,
			32,
			64,
			128,
			256
	)
			.map(number -> String.format("images/icons/icon_%d.png", number))
			.map(ImageHelper::readImageFromResources)
			.toList();

	public GenericWindow() {
		setIconImages(ICONS);
	}
}

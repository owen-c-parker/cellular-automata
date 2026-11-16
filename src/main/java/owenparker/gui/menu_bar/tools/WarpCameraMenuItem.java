package owenparker.gui.menu_bar.tools;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigBigInteger;
import owenparker.gui.ConfirmationWindow;
import owenparker.gui.context.DisplayContext;

import javax.swing.*;
import java.math.BigInteger;

public class WarpCameraMenuItem extends JMenuItem {
	private static class WarpCameraConfig extends Configuration {
		public final ConfigBigInteger locationX = new ConfigBigInteger("X", BigInteger.valueOf(100));
		public final ConfigBigInteger locationY = new ConfigBigInteger("Y", BigInteger.valueOf(100));
	}

	public WarpCameraMenuItem() {
		super("Warp camera");
		addActionListener(_ ->
				new ConfirmationWindow<>(new WarpCameraConfig(), config ->
						DisplayContext.gridDisplay.setCamera(config.locationX.get(), config.locationY.get()), "Warp"));
	}
}

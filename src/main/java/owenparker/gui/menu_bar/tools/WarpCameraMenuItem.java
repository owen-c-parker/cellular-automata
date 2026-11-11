package owenparker.gui.menu_bar.tools;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigBigInteger;
import owenparker.gui.AccessorComponent;
import owenparker.gui.ConfirmationWindow;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import java.math.BigInteger;

public class WarpCameraMenuItem extends AccessorComponent<JMenuItem> {
	private static class WarpCameraConfig extends Configuration {
		public final ConfigBigInteger locationX = new ConfigBigInteger("X", BigInteger.valueOf(100));
		public final ConfigBigInteger locationY = new ConfigBigInteger("Y", BigInteger.valueOf(100));
	}

	public WarpCameraMenuItem(GlobalReference globalReference) {
		super(globalReference, new JMenuItem("Warp camera"));
		getComponent().addActionListener(_ -> {
			new ConfirmationWindow<>(new WarpCameraConfig(), config -> {
				globalReference.gridDisplay.setCamera(config.locationX.get(), config.locationY.get());
			}, "Warp");
		});
	}
}

package owenparker.config;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Configuration extends LinkedList<ConfigEntry<?>> {
	public JPanel getPanel() {
		JPanel panel = new JPanel();

		forEach(entry -> {
			panel.add(entry.getController());
		});

		return panel;
	}
}

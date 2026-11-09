package owenparker.gui;

import owenparker.plugins.DefaultPlugin;
import owenparker.plugins.Plugin;
import owenparker.plugins.PluginLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

public class Launcher {

	public static final String APP_NAME_NO_SPACES = "CellularAutomatonSimulator";

	public static final LinkedList<Plugin> installedPlugins = new LinkedList<>();

	public static void errorWindow(Exception e) {
		GenericWindow errorWindow = new GenericWindow();
		errorWindow.setTitle(String.format("Error - %s", e.getClass().getSimpleName()));
		errorWindow.getContentPane().add(new JScrollPane(new JLabel(TextUtils.convertToHTML(e.toString()))));
		errorWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		errorWindow.setSize(screen.width / 2, screen.height / 2);
		errorWindow.setLocationRelativeTo(null);
		errorWindow.setVisible(true);
	}

    public static <T> Collection<T> getAllOfAResource(Function<Plugin, Collection<T>> resourceGetter) {
        LinkedList<T> resources = new LinkedList<>();

        for (Plugin plugin : installedPlugins)
            resources.addAll(resourceGetter.apply(plugin));

        return resources;
    }

    void main() {
		launch();
	}

	private void launch() {
		installedPlugins.add(new DefaultPlugin());
		PluginLoader.loadAvailablePlugins();
		new AutomatonWindow();
	}
}

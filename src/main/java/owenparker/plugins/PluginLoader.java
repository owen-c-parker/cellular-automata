package owenparker.plugins;

import owenparker.gui.FileManagement;
import owenparker.gui.Launcher;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class PluginLoader {

	public static void loadAvailablePlugins() {
		try (Stream<Path> paths = Files.list(FileManagement.getPluginsDirectory().toPath())) {
			paths
					.map(Path::toUri)
					.map(File::new)
					.forEach(PluginLoader::loadPluginFile)
			;
		} catch (IOException e) {
			Launcher.errorWindow(e);
		}
	}

	private static void loadPluginFile(File file) {
		try {
			ServiceLoader<Plugin> pluginServiceLoader = ServiceLoader.load(Plugin.class, new URLClassLoader(new URL[] { file.toURI().toURL() }));
			pluginServiceLoader
					.stream()
					.map(ServiceLoader.Provider::get)
					.forEach(Plugin::load);

		} catch (Exception e) {
			Launcher.errorWindow(new Exception(String.format("Failed to load plugin\n%s", e)));
		}

	}
}

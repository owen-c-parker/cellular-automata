package owenparker.gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagement {
    private static final String CONFIG_FILE = "config.txt";

    static {
		initializeAllDirectories();
	}

	private static void initializeAllDirectories() {
		getSavesDirectory();
		getPluginsDirectory();
	}

	public static File getPluginsDirectory() {
		return createDirectoryIfNotPresent(getMainDirectoryPath().resolve("plugins"));
	}

	public static File getMainDirectory() {
		Path path = getMainDirectoryPath();

		return createDirectoryIfNotPresent(path);
	}

	private static Path getMainDirectoryPath() {
		String userHomeDirectory = System.getProperty("user.home");
		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("win")) {
			String appData = System.getenv("APPDATA");
			if (appData != null)
				return Paths.get(appData, Launcher.APP_NAME_NO_SPACES);
			return Paths.get(userHomeDirectory, "AppData", "Roaming", Launcher.APP_NAME_NO_SPACES);
		}

		if (osName.contains("mac"))
			return Paths.get(userHomeDirectory, "Library", "Application Support", Launcher.APP_NAME_NO_SPACES);

		String xdgData = System.getenv("XDG_DATA_HOME");
		if (xdgData != null)
			return Paths.get(xdgData, Launcher.APP_NAME_NO_SPACES);
		return Paths.get(userHomeDirectory, ".local", "share", Launcher.APP_NAME_NO_SPACES);
	}

	private static File createDirectoryIfNotPresent(Path path) {
		try {
			return Files.createDirectories(path).toFile();
		} catch (IOException e) {
			Launcher.errorWindow(e);
		}
		return null;
	}

	public static File getSavesDirectory() {
		Path path = getMainDirectoryPath().resolve("saves");

		return createDirectoryIfNotPresent(path);
	}

    public static File getConfigFile() {
        return Paths.get(FileManagement.getMainDirectory().getPath(), FileManagement.CONFIG_FILE).toFile();
    }
}

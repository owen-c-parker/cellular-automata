package owenparker.gui;

import owenparker.lang.Language;
import owenparker.plugins.DefaultPlugin;
import owenparker.plugins.Plugin;
import owenparker.plugins.PluginLoader;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Launcher {

	public static final String APP_NAME_NO_SPACES = "CellularAutomatonSimulator";

	public static final LinkedList<Plugin> installedPlugins = new LinkedList<>();

	public static void errorWindow(Exception e) {
		GenericWindow errorWindow = new GenericWindow();
		errorWindow.setTitle(String.format(Language.currentLanguage.get(Language.Key.ERROR_WINDOW_TITLE), e.getClass().getSimpleName()));
		errorWindow.getContentPane().add(new JScrollPane(new JLabel(TextUtils.convertToHTML(e.toString()))));
		errorWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		errorWindow.setSize(screen.width / 2, screen.height / 2);
		errorWindow.setLocationRelativeTo(null);
		errorWindow.setVisible(true);
	}

	void main() {
		launch();
	}

	private void launch() {
		//setLookAndFeel();
		installedPlugins.add(new DefaultPlugin());
		PluginLoader.loadAvailablePlugins();
		Language.currentLanguage = installedPlugins.getFirst().getLanguages().getFirst();
		new AutomatonWindow();
	}

	private void setLookAndFeel() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

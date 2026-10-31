package gui.OLD.automaton_display.menu_options.file_options;

import engine.Automaton;
import gui.Launcher;
import gui.OLD.automaton_display.AutomatonDisplayer;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class FileNew extends JMenu {
	private static class NewAutomatonButton extends JMenuItem {
		public NewAutomatonButton(Class<? extends Automaton> clazz, AutomatonDisplayer displayer) throws InvocationTargetException, InstantiationException, IllegalAccessException {
			Automaton automaton = ((Automaton) clazz.getDeclaredConstructors()[0].newInstance());
			super(automaton.getDisplayableName());
			addActionListener(_ -> {
				displayer.setAutomaton(automaton);
			});
		}
	}

	public FileNew(AutomatonDisplayer automatonDisplayer) {
		super("New");
		Collection<Class<? extends Automaton>> automatonClasses = Launcher.getAvailableAutomatonClasses();
		for (Class<? extends Automaton> clazz : automatonClasses) {
			try {
				add(new NewAutomatonButton(clazz, automatonDisplayer));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}

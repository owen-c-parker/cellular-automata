package owenparker.gui.context;

import owenparker.config.Configuration;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.NoConfigAutomatonFactory;
import owenparker.engine.exceptions.InvalidConfigException;
import owenparker.gui.ConfirmationWindow;
import owenparker.gui.Launcher;

public abstract class SimulationContext {
	public static boolean currentlyPlaying = false;
	public static double ticksPerMillisecond = 8.0 / 1000;
	public static Automaton automaton;

	public static Automaton getAutomaton() {
		return automaton;
	}

	public static void setAutomaton(Automaton automaton) {
		SimulationContext.automaton = automaton;
		DisplayContext.setSelectedForPlacement(automaton.getSelectedByDefault());
		DisplayContext.resetCamera();
		Events.newAutomaton.fire();
	}

	public static void step() {
		SimulationContext.automaton.step();
		Events.repaint.fire();
	}

	public static <T extends Configuration> void buildAutomatonFromFactory(AutomatonFactory<T> factory) {
		if (factory instanceof NoConfigAutomatonFactory noConfigFactory) {
			setAutomaton(noConfigFactory.buildAutomaton());
			return;
		}

		T config = factory.getConfig();
		String title = String.format("Create %s", factory.getDisplayableName());
		new ConfirmationWindow<T>(
				config,
				filledConfig -> {
					try {
						setAutomaton(factory.buildAutomaton(filledConfig));
					} catch (InvalidConfigException e) {
						Launcher.errorWindow(e);
					}
				},
				title
		);
		try {
			setAutomaton(factory.buildAutomaton(config));
		} catch (InvalidConfigException e) {
			Launcher.errorWindow(e);
		}
	}
}
package owenparker.gui;

import owenparker.config.Configuration;
import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;
import owenparker.engine.exceptions.InvalidConfigException;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.preferences.Preferences;


/**
 * This class was created as a desire to avoid using static variables, as it makes testing difficult. I now feel that
 * it has just kicked the can down the road. It might be worth it to refactor this class out of existence.
 */
public class GlobalReference {
    public boolean currentlyPlaying = false;
    public double ticksPerMillisecond = 8.0 / 1000;
	private Automaton automaton;
	public CursorPlaceable selectedForPlacement;
	private CursorMode currentCursorMode = CursorMode.PENCIL;
	public GridDisplay gridDisplay;

	public final EventNotifier cursorModeChangeEvent = new EventNotifier();
	public final EventNotifier speedChangeEvent = new EventNotifier();
    public final EventNotifier repaintEvent = new EventNotifier();
    public final EventNotifier newAutomatonEvent = new EventNotifier();
	public final EventNotifier newColorPaletteEvent = new EventNotifier();

    public final Preferences preferences = new Preferences();

    public GlobalReference() {
		this.preferences.selectedTheme.addSetListener(this.newColorPaletteEvent::fire);
		this.preferences.selectedTheme.addSetListener(this.repaintEvent::fire);
    }

    public Automaton getAutomaton() {
        return this.automaton;
    }

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
        this.selectedForPlacement = automaton.getSelectedByDefault();
		resetCamera();
        this.newAutomatonEvent.fire();
    }

    public void step() {
        this.automaton.step();
        this.repaintEvent.fire();
    }

	public ColorPalette getColorPalette() {
		return this.preferences.selectedTheme.get();
	}

	public void setBorderPolicy(BorderPolicy borderPolicy) {
		this.automaton.setBorderPolicy(borderPolicy);
		this.repaintEvent.fire();
	}

	public void resetCamera() {
		if (this.gridDisplay == null)
			return;
		this.gridDisplay.resetCamera();
		this.repaintEvent.fire();
	}

	public CursorMode getCurrentCursorMode() {
		return this.currentCursorMode;
	}

	public void setCurrentCursorMode(CursorMode currentCursorMode) {
		this.currentCursorMode = currentCursorMode;
		this.cursorModeChangeEvent.fire();
	}

	public <T extends Configuration> void buildAutomatonFromFactory(AutomatonFactory<T> factory) {
		if (factory instanceof NoConfigAutomatonFactory noConfigFactory) {
			setAutomaton(noConfigFactory.buildAutomaton());
			return;
		}

		T config = factory.getConfig();
		String title = String.format("Create %s", factory.getDisplayableName());
		new ConfirmationWindow<>(
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

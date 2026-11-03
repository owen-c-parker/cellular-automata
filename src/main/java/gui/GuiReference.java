package gui;

import engine.Automaton;
import engine.CursorPlaceable;
import engine.colors.ColorPalette;
import gui.grid.GridDisplay;

import javax.swing.*;

public class GuiReference {
    public boolean currentlyPlaying = false;
    public double ticksPerMillisecond = 8.0 / 1000;
    private Automaton automaton;
    public CursorPlaceable selectedForPlacement;
    public GridDisplay gridDisplay;

    public final EventNotifier speedChangeEvent = new EventNotifier();
    public final EventNotifier repaintEvent = new EventNotifier();
    public final EventNotifier newAutomatonEvent = new EventNotifier();
	public final EventNotifier newColorPaletteEvent = new EventNotifier();

	public Automaton getAutomaton() {
        return this.automaton;
    }

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
        this.selectedForPlacement = automaton.getSelectedByDefault();
        this.newAutomatonEvent.fire();
    }

    public void step() {
        this.automaton.step();
        this.repaintEvent.fire();
    }

	private ColorPalette colorPalette;

	public void setColorPalette(ColorPalette colorPalette) {
		this.colorPalette = colorPalette;

		UIManager.put("control", colorPalette.getColor(ColorPalette.Slot.BACKGROUND));
		UIManager.put("text", colorPalette.getColor(ColorPalette.Slot.FOREGROUND));
		UIManager.put("nimbusBase", colorPalette.getColor(ColorPalette.Slot.MIDDLE));

		UIManager.put("info", colorPalette.getColor(ColorPalette.Slot.ACTIVE_1).brighter());
		UIManager.put("nimbusInfoBlue", colorPalette.getColor(ColorPalette.Slot.ACTIVE_2).brighter());

		this.newColorPaletteEvent.fire();
		this.repaintEvent.fire();
	}

	public ColorPalette getColorPalette() {
		return this.colorPalette;
	}
}

package gui;

import engine.Automaton;
import engine.CursorPlaceable;
import gui.grid.GridDisplay;

public class GuiReference {
    public boolean currentlyPlaying = false;
    public double ticksPerMillisecond = 8.0 / 1000;
    private Automaton automaton;
    public CursorPlaceable selectedForPlacement;
    public GridDisplay gridDisplay;

    public final EventNotifier speedChangeEvent = new EventNotifier();
    public final EventNotifier repaintEvent = new EventNotifier();
    public final EventNotifier newAutomatonEvent = new EventNotifier();

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
}

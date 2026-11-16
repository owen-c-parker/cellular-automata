package owenparker.gui.context;

import owenparker.gui.EventNotifier;

public abstract class Events {
	public static final EventNotifier mouseMove = new EventNotifier();
	public static final EventNotifier cursorModeChange = new EventNotifier();
	public static final EventNotifier speedChange = new EventNotifier();
	public static final EventNotifier repaint = new EventNotifier();
	public static final EventNotifier newAutomaton = new EventNotifier();
	public static final EventNotifier newColorPalette = new EventNotifier();
}

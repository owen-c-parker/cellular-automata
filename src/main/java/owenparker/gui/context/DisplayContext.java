package owenparker.gui.context;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.CursorMode;
import owenparker.gui.grid.GridDisplay;
import owenparker.gui.preferences.Preferences;


public abstract class DisplayContext {
	public static CursorPlaceable selectedForPlacement;
	private static CursorMode currentCursorMode = CursorMode.PENCIL;
	public static GridDisplay gridDisplay;

	public static void setSelectedForPlacement(CursorPlaceable selectedByDefault) {
		DisplayContext.selectedForPlacement = selectedByDefault;
	}

	public static ColorPalette getColorPalette() {
		return Preferences.getInstance().selectedTheme.get();
	}

	public static void resetCamera() {
		if (gridDisplay == null)
			return;
		gridDisplay.resetCamera();
		Events.repaint.fire();
	}

	public static CursorMode getCurrentCursorMode() {
		return currentCursorMode;
	}

	public static void setCurrentCursorMode(CursorMode currentCursorMode) {
		DisplayContext.currentCursorMode = currentCursorMode;
		Events.cursorModeChange.fire();
	}
}

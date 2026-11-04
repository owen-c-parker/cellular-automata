package owenparker.gui;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.lang.Language;

import javax.swing.*;

public abstract class AccessorComponent<COMPONENT_TYPE extends JComponent> {
    public final GlobalReference globalReference;
    private COMPONENT_TYPE component;
    public AccessorComponent(GlobalReference globalReference, COMPONENT_TYPE component) {
        this.component = component;
        this(globalReference);
	}

    public AccessorComponent(GlobalReference globalReference) {
        this.globalReference = globalReference;
		if (getComponent() instanceof JButton)
			getComponent().setForeground(globalReference.getColorPalette().getColor(ColorPalette.Slot.BACKGROUND));
    }

    public void setComponent(COMPONENT_TYPE component) {
        this.component = component;
    }

    public COMPONENT_TYPE getComponent() {
        return this.component;
    }

	public static String translation(Language.Key key) {
		return Language.getCurrentLanguage().get(key);
	}
}

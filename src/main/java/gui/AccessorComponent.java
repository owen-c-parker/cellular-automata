package gui;

import engine.colors.ColorPalette;

import javax.swing.*;

public abstract class AccessorComponent<COMPONENT_TYPE extends JComponent> {
    public final GuiReference guiReference;
    private COMPONENT_TYPE component;
    public AccessorComponent(GuiReference guiReference, COMPONENT_TYPE component) {
        this.component = component;
        this(guiReference);
	}

    public AccessorComponent(GuiReference guiReference) {
        this.guiReference = guiReference;
		if (getComponent() instanceof JButton)
			getComponent().setForeground(guiReference.getColorPalette().getColor(ColorPalette.Slot.BACKGROUND));
    }

    public void setComponent(COMPONENT_TYPE component) {
        this.component = component;
    }

    public COMPONENT_TYPE getComponent() {
        return this.component;
    }
}

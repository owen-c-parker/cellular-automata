package gui;

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
    }

    public void setComponent(COMPONENT_TYPE component) {
        this.component = component;
    }

    public COMPONENT_TYPE getComponent() {
        return this.component;
    }
}

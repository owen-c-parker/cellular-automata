package gui;

import javax.swing.*;

public abstract class AccessorComponent<COMPONENT_TYPE extends JComponent> {
    public final AutomatonReference automatonReference;
    private COMPONENT_TYPE component;
    public AccessorComponent(AutomatonReference automatonReference, COMPONENT_TYPE component) {
        this.component = component;
        this(automatonReference);
    }

    public AccessorComponent(AutomatonReference automatonReference) {
        this.automatonReference = automatonReference;
    }

    public void setComponent(COMPONENT_TYPE component) {
        this.component = component;
    }

    public COMPONENT_TYPE getComponent() {
        return this.component;
    }
}

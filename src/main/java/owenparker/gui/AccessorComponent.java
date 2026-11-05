package owenparker.gui;

import owenparker.engine.colors.ColorPalette;
import owenparker.gui.lang.Language;

import javax.swing.*;
import java.util.concurrent.Callable;
import java.util.function.Function;

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

	public final void add(AccessorComponent<?> child) {
		getComponent().add(child.getComponent());
	}

	public final void add(Function<GlobalReference, AccessorComponent<?>> childGetter) {
		try {
			add(childGetter.apply(this.globalReference));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

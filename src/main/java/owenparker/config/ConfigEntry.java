package owenparker.config;

import owenparker.gui.EventNotifier;

import javax.swing.*;
import java.io.Serializable;

public abstract class ConfigEntry<T> implements Serializable {
    private T value;
    private final EventNotifier setNotifier = new EventNotifier();

    protected abstract JComponent getControlComponent();

    public JPanel getControlPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(String.format("%s: ", getName())));
        panel.add(getControlComponent());
        return panel;
    }

    private final String name;

    public ConfigEntry(String name, T initialValue) {
        this(name);
        this.value = initialValue;
    }

    public ConfigEntry(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void set(T value) {
        this.value = value;
        this.setNotifier.fire();
    }

    public T get() {
        return this.value;
    }

    public String getConfigLine() {
        return String.format("%s={%s};", getName(), get());
    }

    public void addSetListener(Runnable runnable) {
        this.setNotifier.add(runnable);
    }

    public abstract void attemptSet(Object value);
}

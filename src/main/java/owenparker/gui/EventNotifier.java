package owenparker.gui;

import java.util.LinkedList;

public class EventNotifier extends LinkedList<Runnable> {
    public void fire() {
        for (Runnable runnable : this) {
            runnable.run();
        }
    }
}

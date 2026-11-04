package owenparker.engine;

import java.io.Serializable;

/**
 * Automaton factories is displayed in the "new" menu under "file"
 */
public abstract class AutomatonFactory implements HasDisplayableName, Serializable {
    public abstract Automaton buildAutomaton();
}

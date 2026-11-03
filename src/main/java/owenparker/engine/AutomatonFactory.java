package owenparker.engine;

/**
 * Automaton factories is displayed in the "new" menu under "file"
 */
public abstract class AutomatonFactory implements HasDisplayableName {
    public abstract Automaton buildAutomaton();
}

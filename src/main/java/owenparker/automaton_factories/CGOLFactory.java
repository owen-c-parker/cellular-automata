package owenparker.automaton_factories;

import owenparker.automata.CGOL;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;

public class CGOLFactory extends AutomatonFactory {
    @Override
    public Automaton buildAutomaton() {
        return new CGOL();
    }

    @Override
    public String getDisplayableName() {
        return "Conway's Game of Life";
    }
}

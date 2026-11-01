package automaton_factories;

import automata.CGOL;
import engine.Automaton;
import engine.AutomatonFactory;

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

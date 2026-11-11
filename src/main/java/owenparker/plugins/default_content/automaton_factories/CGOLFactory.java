package owenparker.plugins.default_content.automaton_factories;

import owenparker.plugins.default_content.automata.CGOL;
import owenparker.engine.Automaton;
import owenparker.engine.NoConfigAutomatonFactory;

public class CGOLFactory extends NoConfigAutomatonFactory {
    @Override
    public Automaton buildAutomaton() {
        return new CGOL();
    }

    @Override
    public String getDisplayableName() {
        return "Conway's Game of Life";
    }
}

package automata;

import engine.*;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class RulestringAutomaton extends Automaton {
    private final Collection<Integer> birthNumbers;
    private final Collection<Integer> surviveNumbers;

    private final State ON = new State(Color.LIGHT_GRAY, "Alive") {
        @Override
        public State getNewState(Board board, OrderedPair location) {
            int aliveCount = 0;
            for (State n : board.getNeighborsOf(Neighborhood.MOORE_NEIGHBORHOOD, location))
                if (n.equals(RulestringAutomaton.this.ON))
                    aliveCount++;

            return RulestringAutomaton.this.surviveNumbers.contains(aliveCount) ? RulestringAutomaton.this.ON : RulestringAutomaton.this.OFF;
        }
    };

    private final State OFF = new State(Color.DARK_GRAY, "Dead") {
        @Override
        public State getNewState(Board board, OrderedPair location) {
            int aliveCount = 0;
            for (State n : board.getNeighborsOf(Neighborhood.MOORE_NEIGHBORHOOD, location))
                if (n.equals(RulestringAutomaton.this.ON))
                    aliveCount++;

            return RulestringAutomaton.this.birthNumbers.contains(aliveCount) ? RulestringAutomaton.this.ON : RulestringAutomaton.this.OFF;
        }
    };

    public RulestringAutomaton(Collection<Integer> birthNumbers, Collection<Integer> surviveNumbers) {
        super(Neighborhood.MOORE_NEIGHBORHOOD);
        this.birthNumbers = birthNumbers;
        this.surviveNumbers = surviveNumbers;
    }

    @Override
    public List<State> getSelectableCellStates() {
        return List.of(
                this.OFF,
                this.ON
        );
    }

    @Override
    public State getDefaultState() {
        return this.OFF;
    }

    @Override
    public String getDisplayableName() {
        return String.format("B%s/S%s", this.birthNumbers, this.surviveNumbers);
    }
}

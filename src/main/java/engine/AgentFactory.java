package engine;

public interface AgentFactory extends CursorPlaceable, HasDisplayableName {
    Agent buildAgent(OrderedPair position);
}

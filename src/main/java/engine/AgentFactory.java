package engine;

import java.io.Serializable;

public interface AgentFactory extends CursorPlaceable, HasDisplayableName, Serializable {
    Agent buildAgent(OrderedPair position);
}

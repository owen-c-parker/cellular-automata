package engine;

import java.awt.*;

public interface AgentFactory extends CursorPlaceable, HasDisplayableName {
    Agent buildAgent(OrderedPair position);
}

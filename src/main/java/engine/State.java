package engine;

import java.awt.*;

public interface State {
	State getNewState(Board board, OrderedPair location);
	Color getColor();
	String getDisplayableName();
}

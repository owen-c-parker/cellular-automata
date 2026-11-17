package owenparker.engine.board;

import owenparker.engine.OrderedPair;
import owenparker.engine.State;
import owenparker.gui.preferences.Preferences;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CellUpdateTask implements Callable<State> {
	public static ExecutorService executorService;

	private final OrderedPair location;
	private final Board source;
	private final Board destination;

	static {
		updateExecutorService();
		Preferences.getInstance().threadCount.addSetListener(CellUpdateTask::updateExecutorService);
	}

	private static void updateExecutorService() {
		executorService = Executors.newFixedThreadPool(Preferences.getInstance().threadCount.get());
	}

	public CellUpdateTask(OrderedPair location, Board source, Board destination) {
		this.location = location;
		this.source = source;
		this.destination = destination;
	}

	@Override
	public State call() {
		State oldState = source.getCellAt(location);
		State newState = oldState.getNewState(source, location);
		if (!newState.equals(oldState))
			destination.setCellAt(location, newState);
		return newState;
	}
}

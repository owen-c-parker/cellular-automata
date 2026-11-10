package owenparker.engine.exceptions;

public class InvalidConfigException extends Exception {
	public InvalidConfigException() {
		super("Invalid automaton factory configuration");
	}
}

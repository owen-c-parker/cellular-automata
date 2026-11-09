package owenparker.engine;

public enum BorderPolicy implements HasDisplayableName {
	NO_BORDER("No border"),
	FILL_WITH_DEFAULT("Border"),
	LOOP("Repeat");

	private final String name;

	BorderPolicy(String name) {
		this.name = name;
	}

	@Override
	public String getDisplayableName() {
		return this.name;
	}


    @Override
    public String toString() {
        return getDisplayableName();
    }
}

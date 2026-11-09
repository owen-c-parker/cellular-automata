package owenparker.engine;

import owenparker.gui.lang.Language;

public enum BorderPolicy implements HasDisplayableName {
	NO_BORDER(Language.Key.NO_BORDER),
	FILL_WITH_DEFAULT(Language.Key.FIlL_WITH_DEFAULT),
	LOOP(Language.Key.LOOP);

	private final Language.Key key;

	BorderPolicy(Language.Key key) {
		this.key = key;
	}

	@Override
	public String getDisplayableName() {
		return Language.getCurrentLanguage().get(this.key);
	}


    @Override
    public String toString() {
        return getDisplayableName();
    }
}

package owenparker.lang;

import java.util.Map;

public class EnglishUnitedStates extends Language {
	@Override
	public String getDisplayableName() {
		return "English - USA";
	}

	@Override
	protected Map<Key, String> getMap() {
		return Map.of();
	}

	@Override
	public String getCode() {
		return "en-US";
	}
}

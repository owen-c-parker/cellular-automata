package owenparker.automaton_factories;

import owenparker.automata.Lifelike;
import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigString;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.exceptions.InvalidConfigException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LifeLikeFactory extends AutomatonFactory<LifeLikeFactory.Config> {
	@Override
	public Automaton buildAutomaton(Config config) throws InvalidConfigException {
		String rulestring = config.rulestring.get();

		Rules rules = getRulesFromRulestring(rulestring);

		return new Lifelike() {
			@Override
			protected List<Integer> getBirthNumbers() {
				return rules.birthNumbers;
			}

			@Override
			protected List<Integer> getSurviveNumbers() {
				return rules.surviveNumbers;
			}

			@Override
			public String getDisplayableName() {
				return config.rulestring.get();
			}
		};
	}

	private Rules getRulesFromRulestring(String rulestring) throws InvalidConfigException {
		String[] splitRulestring = rulestring.split("/");

		if (splitRulestring.length != 2)
			throw new InvalidConfigException();

		String birthString = splitRulestring[0];
		String surviveString = splitRulestring[1];

		if (
				birthString.charAt(0) != 'B' ||
				surviveString.charAt(0) != 'S'
		)
			throw new InvalidConfigException();

		birthString = birthString.substring(1);
		surviveString = surviveString.substring(1);

		List<Integer> birth = parseIntoList(birthString);
		List<Integer> survive = parseIntoList(surviveString);

		return new Rules(birth, survive);
	}

	private List<Integer> parseIntoList(String digitString) throws InvalidConfigException {
		List<Integer> list = new ArrayList<>();
		try {
			for (String character : digitString.split("")) {
				if (character.isEmpty())
					continue;
				int integer = Integer.parseInt(character);
				if (integer < 0 || integer > 8)
					throw new InvalidConfigException();
				list.add(integer);
			}
		} catch (NumberFormatException e) {
			throw new InvalidConfigException();
		}
		return list;
	}

	@Override
	public Config getConfig() {
		return new Config();
	}

	@Override
	public String getDisplayableName() {
		return "Lifelike from Rulestring";
	}

	private record Rules(List<Integer> birthNumbers, List<Integer> surviveNumbers) {}

	public static class Config extends Configuration {
		public ConfigString rulestring = new ConfigString("Rulestring", "B3/S23");
	}
}

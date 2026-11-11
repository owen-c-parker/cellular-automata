package owenparker.plugins.default_content.automaton_factories;

import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigString;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.exceptions.InvalidConfigException;
import owenparker.plugins.default_content.automata.turmites.TurmiteAutomaton;
import owenparker.plugins.default_content.automata.turmites.TurnDecision;

import java.util.LinkedList;
import java.util.List;

public class TurmitesFactory extends AutomatonFactory<TurmitesFactory.Config> {

	public static class Config extends Configuration {
		public final ConfigString rulestring = new ConfigString("Turmite rules", "LR");
	}

	@Override
	public Automaton buildAutomaton(Config config) throws InvalidConfigException {
		TurnDecision[] turnDecisions = generateTurnDecisions(config.rulestring.get());
		return new TurmiteAutomaton() {
			@Override
			public String getNameForAgent() {
				return "Turmite";
			}

			@Override
			public TurnDecision[] getTurnDecisions() {
				return turnDecisions;
			}
		};
	}

	private TurnDecision[] generateTurnDecisions(String s) throws InvalidConfigException {
		String[] characters = s.split("");
		List<TurnDecision> decisions = new LinkedList<>();
		for (String character : characters) {
			decisions.add(switch (character) {
				case "R" -> TurnDecision.RIGHT;
				case "L" -> TurnDecision.LEFT;
				case "U" -> TurnDecision.REVERSE;
				case "N" -> TurnDecision.NONE;
				default -> throw new InvalidConfigException();
			});
		}
		return decisions.toArray(new TurnDecision[0]);
	}

	@Override
	public Config getConfig() {
		return new Config();
	}

	@Override
	public String getDisplayableName() {
		return "Turmites";
	}
}

package owenparker.plugins.default_content.automaton_factories;

import owenparker.plugins.default_content.automata.ElementaryAutomaton;
import owenparker.config.Configuration;
import owenparker.config.configuration_entries.ConfigInteger;
import owenparker.engine.Automaton;
import owenparker.engine.AutomatonFactory;
import owenparker.engine.exceptions.InvalidConfigException;

public class ElementaryFactory extends AutomatonFactory<ElementaryFactory.ElementaryConfig> {
	public static class ElementaryConfig extends Configuration {
		public ConfigInteger ruleNumber = new ConfigInteger("Rule number", 110, 0, 255);
	}

	@Override
	public Automaton buildAutomaton(ElementaryConfig config) throws InvalidConfigException {
		return new ElementaryAutomaton() {
			@Override
			protected int getRuleNumber() {
				return config.ruleNumber.get();
			}
		};
	}

	@Override
	public ElementaryConfig getConfig() {
		return new ElementaryConfig();
	}

	@Override
	public String getDisplayableName() {
		return "Elementary Automaton";
	}
}

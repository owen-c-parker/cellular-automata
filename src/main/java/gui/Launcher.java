package gui;

import automaton_factories.*;
import engine.AutomatonFactory;

import java.util.List;

public class Launcher {

	public static List<AutomatonFactory> getAvailableAutomatonClasses() {
		return List.of(
				new CGOLFactory(),
				new KnightCGOLFactory(),
				new Rule110Factory(),
				new WireworldFactory(),
				new BriansBrainFactory(),
				new LangtonsAntFactory()
		);
	}

	void main() {
		launch();
	}

	private void launch() {
        new AutomatonWindow();
	}
}

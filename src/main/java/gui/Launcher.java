package gui;

import automata.CGOL;
import automata.KnightCGOL;
import automata.Rule110;
import automata.Wireworld;
import engine.Automaton;
import gui.automaton_display.AutomatonDisplayer;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Launcher extends JFrame {
	public Launcher() {

		setContentPane(new JPanel());

		getContentPane().add(new JLabel("Select automaton: "));
		JComboBox<Automaton> automatonJComboBox = generateAutomatonComboBox();
		getContentPane().add(automatonJComboBox);
		getContentPane().add(generateLaunchButton(automatonJComboBox));

		setTitle("Cellular Automaton Launcher");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 150);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JButton generateLaunchButton(JComboBox<Automaton> automatonJComboBox) {
		JButton button = new JButton("Launch");

		button.addActionListener(_ -> {
			Automaton automaton = ((Automaton) automatonJComboBox.getSelectedItem());
			launch(automaton);
		});

		return button;
	}

	private void launch(Automaton automaton) {
		AutomatonDisplayer displayer = new AutomatonDisplayer(automaton, 16);
		new AutomatonController(automaton);
		new StatsDisplayer(automaton, displayer);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private JComboBox<Automaton> generateAutomatonComboBox() {

		Automaton[] automata = {
				new CGOL(),
				new KnightCGOL(),
				new Wireworld(),
				new Rule110()
		};

		return new JComboBox<>(automata);
	}
}

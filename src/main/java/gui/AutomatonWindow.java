package gui;

import automata.CGOL;

import javax.swing.*;

public class AutomatonWindow extends JFrame {
    public AutomatonWindow() {
        AutomatonReference automatonReference = new AutomatonReference();
        automatonReference.setAutomaton(new CGOL());

        setContentPane(new JPanel());

        getContentPane().add(new GridDisplay(automatonReference).getComponent());
    }
}

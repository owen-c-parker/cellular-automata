package owenparker.gui.control;

import owenparker.gui.context.Events;
import owenparker.gui.context.SimulationContext;
import owenparker.gui.preferences.Preferences;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SpeedController extends JPanel {
    public SpeedController() {
        super();

		Preferences.getInstance().speedSliderNotchCount.addSetListener(this::refresh);

		refresh();
    }

	private void refresh() {
		removeAll();

		add(createSlider());
		add(createSpeedLabel());
	}

	private JLabel createSpeedLabel() {
        JLabel label = new JLabel();
        Font oldFont = label.getFont();
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, oldFont.getSize()));
        Events.speedChange.add(() -> {

            int longestSpeedStringLength = Math.max(
                    String.valueOf(getSpeedValue(0)).length(),
                    String.valueOf(getSpeedValue(Preferences.getInstance().speedSliderNotchCount.get() - 1)).length()
            );

            String speedString = String.valueOf(SimulationContext.ticksPerMillisecond * 1000);
            label.setText(String.format("%" + longestSpeedStringLength + "s steps per second", speedString));
        });
        return label;
    }

	private double getSpeedValue(int i) {
		return Math.pow(2, i - 3);
	}

	private JSlider createSlider() {
		int maximum = Preferences.getInstance().speedSliderNotchCount.get() - 1;

        JSlider speedSlider = new JSlider();
        speedSlider.setMinimum(0);
        speedSlider.setMaximum(maximum);
        ChangeListener changeListener = _ -> {
            double speedValue = getSpeedValue(speedSlider.getValue());
			SimulationContext.ticksPerMillisecond = speedValue / 1000;
            Events.speedChange.fire();
        };
        changeListener.stateChanged(null);
        speedSlider.addChangeListener(changeListener);
        speedSlider.setValue(Math.min(6, maximum));

        speedSlider.setPaintTrack(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        return speedSlider;
    }
}

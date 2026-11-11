package owenparker.gui.control;

import owenparker.gui.AccessorComponent;
import owenparker.gui.GlobalReference;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SpeedController extends AccessorComponent<JPanel> {
    public SpeedController(GlobalReference globalReference) {
        super(globalReference, new JPanel());

		globalReference.preferences.speedSliderNotchCount.addSetListener(this::refresh);

		refresh();
    }

	private void refresh() {
		getComponent().removeAll();

		getComponent().add(createSlider());
		getComponent().add(createSpeedLabel());
	}

	private JLabel createSpeedLabel() {
        JLabel label = new JLabel();
        Font oldFont = label.getFont();
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, oldFont.getSize()));
        this.globalReference.speedChangeEvent.add(() -> {

            int longestSpeedStringLength = Math.max(
                    String.valueOf(getSpeedValue(0)).length(),
                    String.valueOf(getSpeedValue(this.globalReference.preferences.speedSliderNotchCount.get() - 1)).length()
            );

            String speedString = String.valueOf(this.globalReference.ticksPerMillisecond * 1000);
            label.setText(String.format("%" + longestSpeedStringLength + "s steps per second", speedString));
        });
        return label;
    }

	private double getSpeedValue(int i) {
		return Math.pow(2, i - 3);
	}

	private JSlider createSlider() {
		int maximum = this.globalReference.preferences.speedSliderNotchCount.get() - 1;

        JSlider speedSlider = new JSlider();
        speedSlider.setMinimum(0);
        speedSlider.setMaximum(maximum);
        ChangeListener changeListener = _ -> {
            double speedValue = getSpeedValue(speedSlider.getValue());
            this.globalReference.ticksPerMillisecond = speedValue / 1000;
            this.globalReference.speedChangeEvent.fire();
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

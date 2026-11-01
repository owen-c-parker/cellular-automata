package gui.control;

import gui.AccessorComponent;
import gui.GuiReference;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class SpeedController extends AccessorComponent<JPanel> {
    private final double[] speedValues = {
            .125,
            .25,
            .5,
            1,
            2,
            4,
            8,
            20,
            40,
            80,
            200,
            400,
            800,
            2000,
            4000,
            8000,
            20000,
            40000,
            80000
    };

    public SpeedController(GuiReference guiReference) {
        super(guiReference, new JPanel());

        getComponent().add(createSlider());
        getComponent().add(createSpeedLabel());
    }

    private JLabel createSpeedLabel() {
        JLabel label = new JLabel();
        Font oldFont = label.getFont();
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, oldFont.getSize()));
        this.guiReference.speedChangeEvent.add(() -> {

            int longestSpeedStringLength = Math.max(
                    String.valueOf(this.speedValues[0]).length(),
                    String.valueOf(this.speedValues[this.speedValues.length - 1]).length()
            );

            String speedString = String.valueOf(this.guiReference.ticksPerMillisecond * 1000);
            label.setText(String.format("%" + longestSpeedStringLength + "s steps/s", speedString));
        });
        return label;
    }

    private JSlider createSlider() {
        JSlider speedSlider = new JSlider();
        speedSlider.setMinimum(0);
        speedSlider.setMaximum(this.speedValues.length - 1);
        ChangeListener changeListener = _ -> {
            double speedValue = this.speedValues[speedSlider.getValue()];
            this.guiReference.ticksPerMillisecond = speedValue / 1000;
            this.guiReference.speedChangeEvent.fire();
        };
        changeListener.stateChanged(null);
        speedSlider.addChangeListener(changeListener);
        speedSlider.setValue(6);

        speedSlider.setPaintTrack(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        return speedSlider;
    }
}

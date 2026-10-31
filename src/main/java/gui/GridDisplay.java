package gui;

import engine.OrderedPair;

import javax.swing.*;
import java.awt.*;

public class GridDisplay extends AccessorComponent<JPanel> {
    private static final double ZOOM_AMOUNT = .1;
    private static final double MIN_SCALE = 1;
    private static final double SCALE_TO_STOP_DRAWING_GRIDLINES = 20;
    private static final double MAX_SCALE = 100;
    private static final double DEFAULT_CELL_SCALE = 20;
    private static final Color BORDER_COLOR = Color.BLACK;

    private double cellScale = DEFAULT_CELL_SCALE;
    private double scrollX = 0;
    private double scrollY = 0;

    public GridDisplay(AutomatonReference automatonReference) {
        super(automatonReference);

        setComponent(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                fillBackgroundWithDefaultCellColor(g);
                drawAllCells(g);
                drawAllAgents(g);
                if (GridDisplay.this.cellScale >= SCALE_TO_STOP_DRAWING_GRIDLINES)
                    drawGridlines(g);
            }
        });
    }

    private void drawAllCells(Graphics g) {
        for (OrderedPair location : getCellLocationsOnScreen())
            drawCellAt(g, location);
    }

    private void fillBackgroundWithDefaultCellColor(Graphics g) {
        OrderedPair screenSizeInPixels = getScreenSizeInPixels();
        g.setColor(this.automatonReference.getAutomaton().getDefaultState().getColor());
        g.fillRect(0, 0, screenSizeInPixels.x(), screenSizeInPixels.y());
    }

    private OrderedPair getScreenSizeInPixels() {
        Dimension dimension = getComponent().getSize();
        return new OrderedPair(dimension.width, dimension.height);
    }
}

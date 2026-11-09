package owenparker.gui.export;

import owenparker.engine.*;
import owenparker.engine.colors.ColorPalette;
import owenparker.gui.Launcher;
import owenparker.gui.export.exporters.BMPExporter;
import owenparker.gui.export.exporters.JPGExporter;
import owenparker.gui.export.exporters.PNGExporter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public abstract class Exporter implements HasDisplayableName {

    protected FileFilter getFileFilter() {
        return new FileNameExtensionFilter(getDisplayableName(), getFileExtension());
    }

    protected abstract String getFileExtension();

    protected String getDefaultFileName() {
        return "." + getFileExtension();
    }

    public void export(ExportConfig config, Automaton automaton) {
        BufferedImage image = createBufferedImage(config, automaton);
        Graphics2D g = image.createGraphics();
        draw(g, config, automaton);
        File file = getFile();
        if (file == null)
            return;
        try {
            saveDataToFile(image, file);
        } catch (IOException e) {
            Launcher.errorWindow(e);
        }
    }

    protected File getFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(getFileFilter());
        chooser.setDialogTitle("Export");
        chooser.setSelectedFile(new File(getDefaultFileName()));
        chooser.showSaveDialog(null);
        return chooser.getSelectedFile();
    }

    public static Exporter[] getExporters() {
        return new Exporter[]{
                new PNGExporter(),
                new JPGExporter(),
                new BMPExporter()
        };
    }

    protected abstract void saveDataToFile(BufferedImage image, File file) throws IOException;

    private BufferedImage createBufferedImage(ExportConfig config, Automaton automaton) {
        OrderedPair[] extent = automaton.getExtent();
        OrderedPair unscaledSize = extent[1].subtract(extent[0]).add(OrderedPair.UNIT);
        OrderedPair scaledSize = unscaledSize.scale(config.scale.get());
        return new BufferedImage(scaledSize.x(), scaledSize.y(), BufferedImage.TYPE_INT_RGB);
    }

    private void draw(Graphics2D g, ExportConfig config, Automaton automaton) {
        double scale = config.scale.get();
        OrderedPair[] extent = automaton.getExtent();
        OrderedPair topLeft = extent[0];
        OrderedPair bottomRight = extent[1];

        int width = bottomRight.x() - topLeft.x();
        int height = bottomRight.y() - topLeft.y();

        Collection<OrderedPair> cellLocations = automaton.getAllCellLocations();
        g.setColor(automaton.getDefaultState().getColorEvaluator().getColor(config.theme.get()));
        g.fillRect(0, 0, (int) ((width + 1) * scale), (int) ((height + 1) * scale));

        for (OrderedPair location : cellLocations) {
            State state = automaton.getCellAt(location);
            g.setColor(state.getColorEvaluator().getColor(config.theme.get()));
            OrderedPair imageSpaceLocation = location.subtract(topLeft).scale(scale);
            g.fillRect(imageSpaceLocation.x(), imageSpaceLocation.y(), (int) Math.ceil(scale), (int) Math.ceil(scale));
            state.customDraw(g.create(imageSpaceLocation.x(), imageSpaceLocation.y(), (int) Math.ceil(scale), (int) Math.ceil(scale)));
        }

        for (Agent agent : automaton.getAgents()) {
            OrderedPair imageSpaceLocation = agent.getPosition().subtract(topLeft).scale(scale);
            agent.draw(g.create(imageSpaceLocation.x(), imageSpaceLocation.y(), (int) Math.ceil(scale), (int) Math.ceil(scale)));
        }
    }

    @Override
    public String toString() {
        return getDisplayableName();
    }
}

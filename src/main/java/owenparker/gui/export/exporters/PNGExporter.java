package owenparker.gui.export.exporters;

import owenparker.gui.export.Exporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PNGExporter extends Exporter {

    @Override
    protected String getFileExtension() {
        return "png";
    }

    @Override
    protected void saveDataToFile(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "png", file);
    }

    @Override
    public String getDisplayableName() {
        return "PNG";
    }
}

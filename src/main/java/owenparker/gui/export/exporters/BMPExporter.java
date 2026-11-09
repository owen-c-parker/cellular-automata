package owenparker.gui.export.exporters;

import owenparker.gui.export.Exporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BMPExporter extends Exporter {
    @Override
    protected String getFileExtension() {
        return "bmp";
    }

    @Override
    protected void saveDataToFile(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "bmp", file);
    }

    @Override
    public String getDisplayableName() {
        return "Bitmap";
    }
}

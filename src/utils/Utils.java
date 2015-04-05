package utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {

    static Logger LOGGER = Logger.getLogger(Utils.class.getName());

    public static BufferedImage loadImage(String fileName) {
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return result;
    }

    public static void saveImage(BufferedImage image, String fileName) {
        File output = new File(fileName);
        try {
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }
}

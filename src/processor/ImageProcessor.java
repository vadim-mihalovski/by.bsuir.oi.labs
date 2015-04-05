package processor;

import constants.Constants;
import processor.provider.AnaglyphProvider;

import java.awt.image.BufferedImage;

public class ImageProcessor {

    private AnaglyphProvider anaglyphProvider = new AnaglyphProvider();

    public BufferedImage buildAnagliph(BufferedImage leftImage,
                                       BufferedImage rightImage, Constants type) {

        return anaglyphProvider.getAnaglyphBuilder(type)
                .buildAnaglyph(leftImage, rightImage);
    }
}

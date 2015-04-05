package processor.provider;

import constants.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnaglyphBuilderImpl implements AnaglyphBuilder {

    private Constants dataType;

    public AnaglyphBuilderImpl(Constants type) {

        this.dataType = type;
    }

    @Override
    public BufferedImage buildAnaglyph(BufferedImage leftImage,
                                       BufferedImage rightImage) {

        double[][] leftMatrix = dataType.getLeftMatrix();
        double[][] rightMatrix = dataType.getRightMatrix();
        BufferedImage sourceImg = new BufferedImage(leftImage.getWidth(),
                leftImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < leftImage.getWidth(); ++x) {
            for (int y = 0; y < leftImage.getHeight(); ++y) {
                Color leftColor = new Color(leftImage.getRGB(x, y));
                Color rightColor = new Color(rightImage.getRGB(x, y));
                int[] lrgb = { leftColor.getRed(), leftColor.getGreen(),
                        leftColor.getBlue() };
                int[] rrgb = { rightColor.getRed(), rightColor.getGreen(),
                        rightColor.getBlue() };
                int[] srgb = { 0, 0, 0 };
                int size = leftMatrix.length;
                for (int i = 0; i < size; ++i) {
                    for (int j = 0; j < size; ++j) {
                        srgb[i] += ((leftMatrix[i][j] == 1 ? leftMatrix[i][j] * lrgb[j] : 0)
                                + (rightMatrix[i][j] == 1 ? rightMatrix[i][j] * rrgb[j] : 0));
                    }
                }
                Color sourceColor = new Color(srgb[0], srgb[1], srgb[2]);
                sourceImg.setRGB(x, y, sourceColor.getRGB());
            }
        }
        return sourceImg;
    }
}

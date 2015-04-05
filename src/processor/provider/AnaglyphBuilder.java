package processor.provider;

import java.awt.image.BufferedImage;

public interface AnaglyphBuilder {

    BufferedImage buildAnaglyph(BufferedImage leftImage,
                                BufferedImage rightImage);
}

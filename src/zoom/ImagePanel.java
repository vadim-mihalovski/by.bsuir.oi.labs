package zoom;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel
{
    BufferedImage image;
    double scale;

    public ImagePanel()
    {
        image = Utils.loadImage("image.jpg");
        scale = 1.0;
        setBackground(Color.WHITE);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double x = (w - scale * imageWidth)/2;
        double y = (h - scale * imageHeight)/2;
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
        at.scale(scale, scale);
        g2.drawRenderedImage(image, at);
    }

    /**
     * For the scroll pane.
     */
    public Dimension getPreferredSize()
    {
        int w = (int)(scale * image.getWidth());
        int h = (int)(scale * image.getHeight());
        return new Dimension(w, h);
    }

    public void setScale(double s)
    {
        scale = s;
        revalidate();      // update the scroll pane
        repaint();
    }
}
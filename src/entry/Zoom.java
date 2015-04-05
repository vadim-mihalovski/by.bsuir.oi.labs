package entry;

import zoom.ImagePanel;
import zoom.ImageZoom;

import javax.swing.*;

public class Zoom {
    public static void main(String[] args)
    {
        ImagePanel panel = new ImagePanel();
        ImageZoom zoom = new ImageZoom(panel);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(zoom.getUIPanel(), "North");
        f.getContentPane().add(new JScrollPane(panel));
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}

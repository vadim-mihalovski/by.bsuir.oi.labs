package entry;

import constants.Constants;
import processor.ImageProcessor;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Anaglyph {

    private static BufferedImage leftImg;
    private static BufferedImage rightImg;
    private static BufferedImage sourceImg;
    private JFrame frame;
    private JLabel left;
    private JLabel right;
    private JLabel sourse;

    public static void main(String[] args) {
        new Anaglyph();
    }

    public Anaglyph() {
        init();
        algorithm();
        createLabelsForFrame();
        addImageLabelsToFrame();
        /*frame.setVisible(true);*/
    }

    private void algorithm() {
        long start = System.currentTimeMillis();
        System.out.println(start);
        ImageProcessor processor = new ImageProcessor();
        leftImg = Utils.loadImage("left.jpg");
        rightImg = Utils.loadImage("right.jpg");
        sourceImg = processor.buildAnagliph(leftImg, rightImg,
                Constants.COLOR);
        Utils.saveImage(sourceImg, "saved.png");
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println("Creation time miles:" + (end - start));
    }

    private void init() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createLabelsForFrame() {
        left = new JLabel( new ImageIcon(leftImg));
        right = new JLabel( new ImageIcon(rightImg));
        sourse = new JLabel( new ImageIcon(sourceImg));
    }

    private void addImageLabelsToFrame() {
        frame.add(left);
        frame.add(right);
        frame.add(sourse);
    }

}

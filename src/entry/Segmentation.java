package entry;

import segmentation.SegmentProcessor;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Segmentation {

    public static final int MODE_CONTINUOUS = 1;
    public static final int MODE_ITERATIVE = 2;
    public static final String FILE_NAME = "image.jpg";
    public static final String SEGMENTATION_JPG = "segmentation.jpg";
    public static final String FILE_NAME_1 = "image1.jpg";
    public static final String SEGMENTATION1_JPG = "segmentation1.jpg";
    private JFrame frame;
    private BufferedImage segmentationImage;
    private BufferedImage originalImage;
    private JLabel segmentation;
    private JLabel original;
    private BufferedImage segmentationImage1;
    private BufferedImage originalImage1;
    private JLabel segmentation1;
    private JLabel original1;

    public static void main(String avg[]){
        new Segmentation();
    }

    public Segmentation() {
        init();
        originalImage = Utils.loadImage(FILE_NAME);
        originalImage1 = Utils.loadImage(FILE_NAME_1);
        segmentationImage = segmentation(MODE_ITERATIVE, originalImage, SEGMENTATION_JPG);
        segmentationImage1 = segmentation(MODE_ITERATIVE, originalImage1, SEGMENTATION1_JPG);
        createLabelsForFrame();
        addImageLabelsToFrame();
        frame.setVisible(true);
    }

    private void init() {
        frame = new JFrame();
        frame.setLayout(new GridLayout(2, 2));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createLabelsForFrame() {
        original = new JLabel( new ImageIcon(originalImage));
        segmentation = new JLabel( new ImageIcon(segmentationImage));
        original1 = new JLabel( new ImageIcon(originalImage1));
        segmentation1 = new JLabel( new ImageIcon(segmentationImage1));
    }

    private void addImageLabelsToFrame() {
        frame.add(original);
        frame.add(segmentation);
        frame.add(original1);
        frame.add(segmentation1);
    }

    private BufferedImage segmentation(int odeIterative, BufferedImage srcImage, String fileName) {
        int k = 5;
        int mode = (odeIterative == MODE_CONTINUOUS) ? MODE_CONTINUOUS : MODE_ITERATIVE;
        SegmentProcessor processor = new SegmentProcessor();
        BufferedImage segmentationImage = processor.execute(srcImage, k, mode);
        Utils.saveImage(segmentationImage, fileName);
        return segmentationImage;
    }
}

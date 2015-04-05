package entry;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Filters {

    public static final String FILE_NAME = "image.jpg";
    public static final String GRAY_SCALE_JPG = "grayscale.jpg";
    public static final String SEPIA_JPG = "sepia.jpg";
    public static final String REVERSE_JPG = "reverse.jpg";
    private JFrame frame;
    private BufferedImage reverseImage;
    private BufferedImage sepiaImage;
    private BufferedImage grayImage;
    private BufferedImage originalImage;
    private JLabel reverse;
    private JLabel original;
    private JLabel gray;
    private JLabel sepia;

    public static void main(String avg[]){
        new Filters();
    }

    public Filters() {
        init();
        originalImage = Utils.loadImage(FILE_NAME);
        convertImages();
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

    private void convertImages() {
        grayImage = grayScale(originalImage);
        sepiaImage = sepia(originalImage);
        reverseImage = reverseImage(originalImage);
    }

    private void createLabelsForFrame() {
        original = new JLabel( new ImageIcon(originalImage));
        gray = new JLabel( new ImageIcon(grayImage));
        sepia = new JLabel( new ImageIcon(sepiaImage));
        reverse = new JLabel( new ImageIcon(reverseImage));
    }

    private void addImageLabelsToFrame() {
        frame.add(original);
        frame.add(gray);
        frame.add(sepia);
        frame.add(reverse);
    }

    private BufferedImage grayScale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed() * .299);
                int green = (int)(c.getGreen() * .587);
                int blue = (int)(c.getBlue() *.114);
                Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                bufferedImage.setRGB(j, i, newColor.getRGB());
            }
        }
        Utils.saveImage(bufferedImage, GRAY_SCALE_JPG);
        return bufferedImage;
    }

    private BufferedImage sepia(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color c = new Color(image.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                int outputRed = (int) ((red * .393) + (green *.769) + (blue * .189));
                int outputGreen = (int) ((red * .349) + (green *.686) + (blue * .168));
                int outputBlue = (int) ((red * .272) + (green *.534) + (blue * .131));

                Color newColor = new Color(truncate(outputRed), truncate(outputGreen), truncate(outputBlue));
                bufferedImage.setRGB(j,i,newColor.getRGB());
            }
        }
        Utils.saveImage(bufferedImage, SEPIA_JPG);
        return bufferedImage;
    }

    private BufferedImage reverseImage (BufferedImage oldImage){

        BufferedImage newImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());

        byte[] oldBytes = getBytes(oldImage);
        byte[] newBytes = getBytes(newImage);

        for (int i = 0; i < oldBytes.length; ++i) {
            newBytes[i] = (byte)(255 - (int)oldBytes[i]);
        }
        Utils.saveImage(newImage, REVERSE_JPG);
        return newImage;
    }

    private static byte[] getBytes (BufferedImage image) {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }

    private int truncate(int value) {
        if(value > 255) {
            return 255;
        } else if(value < 0) {
            return 0;
        } else {
            return value;
        }
    }
}
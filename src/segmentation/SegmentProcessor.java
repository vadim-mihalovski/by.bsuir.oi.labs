package segmentation;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class SegmentProcessor {

    private Cluster[] clusters;
    public static final int MODE_CONTINUOUS = 1; 
    public static final int MODE_ITERATIVE = 2;     
    
    public BufferedImage execute(BufferedImage image, int k, int mode) { 
        int w = image.getWidth();
        int h = image.getHeight(); 
        
        clusters = createClusters(image, k);
        
        int[] lut = new int[w*h];
        Arrays.fill(lut, -1); 
         
        boolean pixelChangedCluster = true;
        while (pixelChangedCluster) {
            pixelChangedCluster = false; 
            for (int y=0;y<h;y++) {
                for (int x=0;x<w;x++) { 
                    int pixel = image.getRGB(x, y); 
                    Cluster cluster = findMinimalCluster(pixel); 
                    if (lut[w*y+x] != cluster.getId()) { 
                        if (mode == MODE_CONTINUOUS) {
                            if (lut[w*y+x] != -1) { 
                                clusters[lut[w*y+x]].removePixel(pixel);
                            } 
                            cluster.addPixel(pixel);
                        } 
                        pixelChangedCluster = true;
                        lut[w*y+x] = cluster.getId();
                    } 
                } 
            } 
            if (mode==MODE_ITERATIVE) {
                for (Cluster cluster : clusters) {
                    cluster.clear();
                } 
                for (int y = 0; y < h; y++) { 
                    for (int x = 0; x < w; x++) { 
                        int clusterId = lut[w*y+x]; 
                        clusters[clusterId].addPixel(image.getRGB(x, y));
                    } 
                } 
            } 
             
        } 
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y=0;y<h;y++) { 
            for (int x=0;x<w;x++) { 
                int clusterId = lut[w*y+x]; 
                result.setRGB(x, y, clusters[clusterId].getRGB()); 
            } 
        } 
        return result;
    } 
     
    private Cluster[] createClusters(BufferedImage image, int k) {
        Cluster[] result = new Cluster[k];
        int x = 0; int y = 0; 
        int dx = image.getWidth() / k; 
        int dy = image.getHeight() / k; 
        for (int i = 0; i < k; i++) { 
            result[i] = new Cluster(i, image.getRGB(x, y)); 
            x += dx; y += dy; 
        } 
        return result; 
    } 
     
    private Cluster findMinimalCluster(int rgb) {
        Cluster cluster = null; 
        int min = Integer.MAX_VALUE;
        for (Cluster cluster1 : clusters) {
            int distance = cluster1.distance(rgb);
            if (distance < min) {
                min = distance;
                cluster = cluster1;
            }
        } 
        return cluster; 
    }
}

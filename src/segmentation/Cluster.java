package segmentation;

public class Cluster {

	int id; 
    int pixelCount; 
    int red, green, blue; 
    int reds, greens, blues; 
     
    public Cluster(int id, int rgb) { 
        this.id = id;        
    	this.red = rgb >> 16&0x000000FF;  
    	this.green = rgb >> 8&0x000000FF; 
    	this.blue = rgb &0x000000FF;
        addPixel(rgb); 
    } 
     
    public void clear() { 
        red = 0; 
        green = 0; 
        blue = 0; 
        reds = 0; 
        greens = 0; 
        blues = 0; 
        pixelCount = 0; 
    } 
     
    int getId() { 
        return id; 
    } 
     
    int getRGB() { 
        int r = reds / pixelCount; 
        int g = greens / pixelCount; 
        int b = blues / pixelCount; 
        return 0xff000000|r<<16|g<<8|b; 
    } 
    
    void addPixel(int color) { 
        pixelCount++;         
        reds += color >>16&0x000000FF; 
        greens += color >> 8&0x000000FF; 
        blues += color &0x000000FF;
        red   = reds / pixelCount; 
        green = greens / pixelCount; 
        blue  = blues / pixelCount; 
    } 
     
    void removePixel(int color) {
        pixelCount--;   
        reds -= color >> 16&0x000000FF; 
        greens -= color >> 8&0x000000FF; 
        blues -= color &0x000000FF;
        red   = reds / pixelCount; 
        green = greens / pixelCount; 
        blue  = blues / pixelCount; 
    } 
     
    int distance(int color) {   
        int rx = Math.abs(red - (color >> 16&0x000000FF)); 
        int gx = Math.abs(green - (color >> 8&0x000000FF)); 
        int bx = Math.abs(blue - (color &0x000000FF));
        return (rx + gx + bx) / 3;
    } 
}

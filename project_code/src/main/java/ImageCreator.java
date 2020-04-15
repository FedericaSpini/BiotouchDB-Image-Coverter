import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImageCreator
{
    private static ImageCreator instance = null;

    public static ImageCreator getInstance(){
        if (instance==null){
            instance = new ImageCreator();
        }
        return instance;
    }

    public void createPointImage(JSONDocRepresentation jsonImg, BufferedImage img, Graphics2D g2){
        ArrayList<SampledPoint[]> strokes = new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
        for (SampledPoint[] stroke: strokes){
            ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
            for (SampledPoint point: points) {
                int alpha = 255;
                int red = 0;
                int green = 0;
                int blue = 0;
                int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
                img.setRGB(Math.round(point.getX()), Math.round(point.getY()), color);
            }
        }

    }

    public void createStrokeImage(JSONDocRepresentation jsonImg, BufferedImage img, Graphics2D g2) {
        ArrayList<SampledPoint[]> strokes = new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
        for (SampledPoint[] stroke : strokes) {
            ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
            SampledPoint oldpoint = points.get(0);

            for (SampledPoint point : points) {
                g2.setColor(Color.BLACK);
                g2.drawLine(Math.round(oldpoint.getX()), Math.round(oldpoint.getY()), Math.round(point.getX()), Math.round(point.getY()));

                oldpoint = point;
            }
        }
    }
    //TODO continua
    public void createStrokeModuledImage(JSONDocRepresentation jsonImg, BufferedImage img, Graphics2D g2, int modul) {
        ArrayList<SampledPoint[]> strokes = new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
        for (SampledPoint[] stroke : strokes) {
            ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
            SampledPoint oldpoint = points.get(0);

            for (SampledPoint point : points) {
                g2.setColor(Color.BLACK);
                g2.drawLine(Math.round(oldpoint.getX()), Math.round(oldpoint.getY()), Math.round(point.getX()), Math.round(point.getY()));

                oldpoint = point;
            }
        }
    }
    public void createBalancedStrokeImage(JSONDocRepresentation jsonImg, BufferedImage img, Graphics2D g2) {

        ArrayList<SampledPoint[]> strokes = new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
        for (SampledPoint[] stroke : strokes) {
            ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
            SampledPoint oldpoint = points.get(0);

            for (SampledPoint point : points) {
                g2.setColor(Color.BLACK);
                int oldX = Math.round(oldpoint.getX());
                int oldY = Math.round(oldpoint.getY());
                int newX = Math.round(point.getX());
                int newY = Math.round(point.getY());
                if(Math.abs(oldX-newX)==1){
                    if (oldX>newX){
                        g2.drawLine(oldX, oldY, oldX, newY/2);
                        g2.drawLine(newX, newY/2, newX, newY/2);
                    }
                    else{
                        g2.drawLine(oldX, oldY, oldX, newY/2);
                        g2.drawLine(newX, newY/2, newX, newY/2);
                } }
                else if (Math.abs(oldY-newY)==1){
                    System.out.format("true %d, %d, %d, %d\n",Math.round(oldX), Math.round(oldY), Math.round(newX), Math.round(newY) );
                } else{
                    g2.drawLine(Math.round(oldX), Math.round(oldY), Math.round(newX), Math.round(newY));
                    }
                oldpoint = point;
            }
        }
    }

    public void createImageFromJson(JSONDocRepresentation jsonImg){
        try {
            int w = jsonImg.getSessionData().getDeviceData().getWidthPixels();
            int h = jsonImg.getSessionData().getDeviceData().getHeigthPixels();
            BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g2 = img.createGraphics();
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int alpha = 255;
                    int red = 255;
                    int green = 255;
                    int blue = 255;
                    int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    img.setRGB(i, j, color);
                }
            }
            createPointImage( jsonImg,  img,  g2);
            File output = new File("saved_point.png");
            ImageIO.write(img, "bmp", output);

            createStrokeImage( jsonImg,  img,  g2);
            File output2 = new File("saved_stroke.png");
            ImageIO.write(img, "bmp", output2);

            createBalancedStrokeImage( jsonImg,  img,  g2);
            File output3 = new File("saved_balanced_stroke.png");
            ImageIO.write(img, "bmp", output3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

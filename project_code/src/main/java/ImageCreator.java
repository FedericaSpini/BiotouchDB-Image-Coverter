import dataStructures.DeviceData;
import dataStructures.SampledPoint;
import dataStructures.MovementPoint;
import dataStructures.SessionData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageCreator
{
    private static ImageCreator instance = null;

    public static ImageCreator getInstance(){
        if (instance==null){
            instance = new ImageCreator();
        }
        return instance;
    }

    /**
     * Plots the points' coordinates couples on the given image
     * @param jsonImg contains the points coordinates
     * @param img
     */
    public void createPointImage(JSONDocRepresentation jsonImg, BufferedImage img){
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

    /**
     * Plots the segments that links the points' coordinates couples on the given image related to g2's image
     * @param jsonImg contains the points coordinates
     * @param g2
     */
    public void createStrokeImage(JSONDocRepresentation jsonImg, Graphics2D g2) {
        ArrayList<SampledPoint[]> strokes = new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
        for (SampledPoint[] stroke : strokes) {
            ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
            SampledPoint oldpoint = points.get(0);

            for (SampledPoint point : points) {
                g2.setColor(Color.BLACK);
                g2.drawLine(Math.round(oldpoint.getX()), Math.round(oldpoint.getY()), Math.round(point.getX()), Math.round(point.getY()));
                //drawLine method has problems when the second point is higher than the first one, so the segment id drawn bidirectionally
                g2.drawLine(Math.round(point.getX()), Math.round(point.getY()), Math.round(oldpoint.getX()), Math.round(oldpoint.getY()));
                oldpoint = point;
            }
        }
    }

    /**
     * Plots the segments that links the points' coordinates couples on the given image related to g2's image
     * @param jsonImg contains the points coordinates
     * @param g2
     * @param module
     */
    public void createStrokeImage(JSONDocRepresentation jsonImg,Graphics2D g2, int module) {
        ArrayList<SampledPoint[]> strokes =new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
        for (SampledPoint[] stroke : strokes) {
            ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
            SampledPoint oldpoint = points.get(0);

            int c = 0;
            for (SampledPoint point : points) {
                c++;
                if (c%module==0) {
                    g2.setColor(Color.BLACK);
                    g2.drawLine(Math.round(oldpoint.getX()), Math.round(oldpoint.getY()), Math.round(point.getX()), Math.round(point.getY()));
                    //drawLine method has problems when the second point is higher than the first one, so the segment id drwn bidirectionally
                    g2.drawLine(Math.round(point.getX()), Math.round(point.getY()), Math.round(oldpoint.getX()), Math.round(oldpoint.getY()));

                    oldpoint = point;
                }
            }
        }
    }

    /**
     * Given an image representation, creates the corrispondent image file
     * @param jsonImg
     */
    public void createImageFromJson(JSONDocRepresentation jsonImg, String destinationPath){
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

            //CHANGE THIS METHOD ACCORDING TO THE ONE TO CHANGE THE IMAGE CREATION MODALITIES
            createStrokeImage( jsonImg, g2);
            File output1 = new File(destinationPath);
            ImageIO.write(img, "bmp", output1);
//            createStrokeImage( jsonImg, g2,5);
//            File output2 = new File("stroke_modulo10_img.png");
//            ImageIO.write(img, "bmp", output2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

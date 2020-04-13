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

    public void createImage(JSONDocRepresentation jsonImg){
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
                    int blue = 50;
                    int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    img.setRGB(i, j, color);
                }
            }
            ArrayList<SampledPoint[]> strokes = new ArrayList<>(Arrays.asList(jsonImg.getSampledPoints()));
            for (SampledPoint[] stroke: strokes){
                ArrayList<SampledPoint> points = new ArrayList<>(Arrays.asList(stroke));
                for (SampledPoint point: points){
                    int alpha = 255;
                    int red = 0;
                    int green = 0;
                    int blue = 0;
                    int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    img.setRGB(Math.round(point.getX()), Math.round(point.getY()), color);
                }
            }





            File output = new File("saved.png");
            ImageIO.write(img, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

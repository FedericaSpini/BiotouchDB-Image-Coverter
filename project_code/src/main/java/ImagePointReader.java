import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This class reads, from the json file representing an image, the coordinates of the move points
 * which compose the strokes of a word
 */
public class ImagePointReader {

    public Object readSingleJson(String path){
        Gson gson = new Gson();
        try(Reader reader = new FileReader(path)){
            // Convert JSON File to Java Object
            Object staff = gson.fromJson(reader, Object.class);

            // print staff
            System.out.println(staff);
            return staff;
        }
        catch(IOException e ){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        ImagePointReader i = new ImagePointReader();
        Object o = i.readSingleJson("c:\\test\\Federica.Spini.BLOCK LETTERS.0.json");
    }


}

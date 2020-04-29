import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This class reads, from the json file representing an image, the coordinates of the move points
 * which compose the strokes of a word
 */
public class ImagePointReader {

    public JSONDocRepresentation readSingleJson(String path){
        Gson gson = new Gson();
        try(Reader reader = new FileReader(path)){
            // Convert JSON File to Java Object
            JSONDocRepresentation staff = gson.fromJson(reader, JSONDocRepresentation.class);
            return staff;
        }
        catch(IOException e ){
            e.printStackTrace();
        }
        return null;
    }
}

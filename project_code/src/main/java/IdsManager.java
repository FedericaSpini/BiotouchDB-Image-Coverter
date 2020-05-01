import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the class that interacts with the userIdentificators.txt file in the project resources. So, with this class,
 * users'identificators can me stored and re-used. So, in different version of the dataset, the same user have always the
 * same identification code.
 */
public class IdsManager {

    private HashMap<String, String> userId;

    public IdsManager(){this.userId = new HashMap<String, String>();}

    public HashMap<String, String> readUserIdentificationFile(){
        try {
            File myObj = new File("usersIdentificators");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public HashMap<String, String> getUserId() {return userId;}

    public void setUserId(HashMap<String, String> userId) {this.userId = userId;}

}

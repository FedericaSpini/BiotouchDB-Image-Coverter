import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the class that interacts with the userIdentificators.txt file in the project resources. So, with this class,
 * users'identificators can me stored and re-used. So, in different version of the dataset, the same user have always the
 * same identification code.
 */
public class IdsManager {

    private HashMap<String, String> userId;
    private int maxId;

    public IdsManager(){
        this.userId = new HashMap<String, String>();
        maxId = 0;
        readUserIdentificationFile();
    }

    private HashMap<String, String> readUserIdentificationFile(){
        try {
            File myObj = new File("usersIdentificators");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] nameId = data.split(":");
                this.userId.put(nameId[0], nameId[1]);
                int newId = Integer.parseInt(nameId[1].substring(1));
                if (newId>this.maxId)this.maxId=newId;
                System.out.println(data + this.userId);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public void addUserId(String name){
        if (!this.userId.containsKey(name)){
            this.maxId++;
            this.userId.put(name, "u"+this.maxId);
        }
    }

    public HashMap<String, String> getUserId() {return userId;}

    public void setUserId(HashMap<String, String> userId) {this.userId = userId;}

    public void updateUserIdentificationFile() throws IOException {
//        new FileWriter("usersIdentificators", false).close();
        FileWriter writer = new FileWriter("usersIdentificators", false);
        for (Map.Entry me : this.userId.entrySet()) {
            writer.write(me.getKey()+":"+me.getValue()+"\n");
            System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue()+"\n");
        }
//        writer.write("BENE");
//        writer.write("ho scritto tutto");
        writer.close();
    }

}

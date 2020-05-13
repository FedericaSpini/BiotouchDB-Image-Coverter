import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BiotouchDatasetImageConverter {
    private File dbFolderPath;
    private File newDbFolderPath;

    public BiotouchDatasetImageConverter(String dbFolderPath, String newDbFolderPath){
        this.dbFolderPath = new File(dbFolderPath);
        this.newDbFolderPath = new File(newDbFolderPath);
    }
    public File getDbFolderPath() {return dbFolderPath;}

    public void setDbFolderPath(File dbFolderPath) {this.dbFolderPath = dbFolderPath;}

    public File getNewDbFolderPath() {return newDbFolderPath;}

    public void setNewDbFolderPath(File newDbFolderPath) {this.newDbFolderPath = newDbFolderPath;}

    /**
     * This function recursively copy all the sub folder and files from sourceFolder to destinationFolder
     * */
    private void makeImgDataset(File sourceFolder, File destinationFolder) throws IOException
    {
        if (sourceFolder.isDirectory())
        {
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
            }
            String files[] = sourceFolder.list();

            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                makeImgDataset(srcFile, destFile);
            }
        }
        else
        {
            if (getFileExtension(sourceFolder).equals("json")) {
                JsonDocReader i = new JsonDocReader();
                JSONDocRepresentation o = i.readSingleJson(sourceFolder.getPath());
                ImageCreator imageCreator = ImageCreator.getInstance();
                imageCreator.createImageFromJson(o, changeFilePathExtension(destinationFolder.getPath(),"png"));
            }
        }
    }

    private void copyFolderAnonimized(File sourceFolder, File destinationFolder, String id) throws IOException
    {
        IdsManager idsManager = new IdsManager();
        String actualUserId = id;

        if (sourceFolder.isDirectory())
        {
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
            }
            String files[] = sourceFolder.list();

            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                String newFileName = srcFile.getName();
                if (file.contains(".")){
                    String[] nameSurname;
                    nameSurname = file.toLowerCase().split("\\.");
                    String userIdCode = idsManager.getUserId(nameSurname[0]+"."+nameSurname[1]);
                    newFileName = userIdCode;
                    actualUserId = userIdCode;
                }
                File destFile = new File(destinationFolder, newFileName);
                copyFolderAnonimized(srcFile, destFile, actualUserId);

            }
            idsManager.updateUserIdentificationFile();
        }
        else
        {
            if (getFileExtension(sourceFolder).equals("json")) {
                JsonDocReader i = new JsonDocReader();
                JSONDocRepresentation o = i.readSingleJson(sourceFolder.getPath());
                JSONDocRepresentationAnonymous newDoc = new JSONDocRepresentationAnonymous(o, id);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter fileWriter = new FileWriter(destinationFolder.getParent().concat("\\"+changeNameWithId(sourceFolder.getName(), id)));
                fileWriter.write(gson.toJson(newDoc));
                fileWriter.close();
            }
            if (getFileExtension(sourceFolder).equals("png")) {

                Files.copy(sourceFolder.toPath(), new File(destinationFolder.getParent().concat("\\"+changeNameWithId(sourceFolder.getName(), id))).toPath());
            }
        }
    }

    private String changeNameWithId(String fileName, String id){
//        System.out.println(fileName);
        String[] fileNameComponents = fileName.split("\\.");
        for (int i = 2; i<fileNameComponents.length;i++){id+= ("."+fileNameComponents[i]);}
//        System.out.println(id);
        return id;
    }

    public JSONDocRepresentationAnonymous getAonymousJSON(JSONDocRepresentation doc, String id){
        return new JSONDocRepresentationAnonymous(doc, id);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    private static String changeFilePathExtension(String fileName, String newExtension) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(0,fileName.lastIndexOf(".")+1)+newExtension;
        else return "";
    }

    public static void main(String[] args) throws IOException {
        //THIS CODE CHANGE A BIOUTOUCH DATASET INTO A CORRESOINDING PICTURE DATASET
//        BiotouchDatasetImageConverter dbConverter = new BiotouchDatasetImageConverter("d:\\test\\dataset", "d:\\test\\imgOriginalDataset");
//        dbConverter.makeImgDataset(dbConverter.getDbFolderPath(),dbConverter.getNewDbFolderPath());

        //IDSMANAGER TEST:
//        IdsManager idsManager = new IdsManager();
//        idsManager.readUserIdentificationFile();
//        idsManager.addUserId("gilia.livilli", "u43");
//        idsManager.updateUserIdentificationFile();

        //THIS CODE TEST HOW TO COPY AN ANONYMIZED VERSION OF A BIOTOUCH DATASET
        BiotouchDatasetImageConverter dbConverter = new BiotouchDatasetImageConverter("d:\\test\\img_biotouch_2018_dataset", "d:\\test\\anonymous_img_biotouch_2018_dataset");
        dbConverter.copyFolderAnonimized(dbConverter.getDbFolderPath(),dbConverter.getNewDbFolderPath(), "");


        
    }
}

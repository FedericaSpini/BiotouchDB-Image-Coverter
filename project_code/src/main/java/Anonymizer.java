import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Anonymizer {

    public static void copyFolderAnonimized(File sourceFolder, File destinationFolder, String id) throws IOException
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

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    public static String changeNameWithId(String fileName, String id){
//        System.out.println(fileName);
        String[] fileNameComponents = fileName.split("\\.");
        for (int i = 2; i<fileNameComponents.length;i++){id+= ("."+fileNameComponents[i]);}
//        System.out.println(id);
        return id;
    }

    public JSONDocRepresentationAnonymous getAonymousJSON(JSONDocRepresentation doc, String id){
        return new JSONDocRepresentationAnonymous(doc, id);
    }

     public static String changeFilePathExtension(String fileName, String newExtension) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(0,fileName.lastIndexOf(".")+1)+newExtension;
        else return "";
    }

}

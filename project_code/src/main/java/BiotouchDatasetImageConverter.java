import dataStructures.DeviceData;
import dataStructures.SampledPoint;
import dataStructures.MovementPoint;
import dataStructures.SessionData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private void copyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        if (sourceFolder.isDirectory())
        {
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
                System.out.println("Directory created :: " + destinationFolder);
            }
            String files[] = sourceFolder.list();

            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            if (getFileExtension(sourceFolder).equals("json")) {
                ImagePointReader i = new ImagePointReader();
                JSONDocRepresentation o = i.readSingleJson(sourceFolder.getPath());
                ImageCreator imageCreator = ImageCreator.getInstance();
                imageCreator.createImageFromJson(o, changeFilePathExtension(destinationFolder.getPath(),"png"));
            }
        }
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
        BiotouchDatasetImageConverter dbConverter = new BiotouchDatasetImageConverter("d:\\test\\datasetTest", "d:\\test\\imgDatasetProva");
        dbConverter.copyFolder(dbConverter.getDbFolderPath(),dbConverter.getNewDbFolderPath());
        
    }
}

import java.io.File;
import java.io.IOException;

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
                System.out.println("Directory created :: " + destinationFolder);
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
                ImagePointReader i = new ImagePointReader();
                JSONDocRepresentation o = i.readSingleJson(sourceFolder.getPath());
                ImageCreator imageCreator = ImageCreator.getInstance();
                imageCreator.createImageFromJson(o, changeFilePathExtension(destinationFolder.getPath(),"png"));
            }
        }
    }

    private void copyFolderAnonimized(File sourceFolder, File destinationFolder) throws IOException
    {
        IdsManager idsManager = new IdsManager();

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
                String newFileName = "";
                if (file.contains(".")){
                    String[] nameSurname;
                    nameSurname = file.toLowerCase().split("\\.");
                    String userName = nameSurname[0]+"."+nameSurname[1];
                    idsManager.addUserId(userName);
                    //ORA AGGIUNGE CORRETTAMENTE GLI ID AL FILE, MA LI DEVE PURE LEGGERE SE GIA' ESISTONO E AGGIORNARE E COPIARE I NOMI DELLE CARTELLE.
                }
                else{
                    File destFile = new File(destinationFolder, newFileName);
                    copyFolderAnonimized(srcFile, destFile);
                }
            }
            idsManager.updateUserIdentificationFile();
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
        //THIS CODE CHANGE A BIOUTOUCH DATASET INTO A CORRESOINDING PICTURE DATASET
//        BiotouchDatasetImageConverter dbConverter = new BiotouchDatasetImageConverter("d:\\test\\dataset", "d:\\test\\imgOriginalDataset");
//        dbConverter.makeImgDataset(dbConverter.getDbFolderPath(),dbConverter.getNewDbFolderPath());

        //IDSMANAGER TEST:
//        IdsManager idsManager = new IdsManager();
//        idsManager.readUserIdentificationFile();
//        idsManager.addUserId("gilia.livilli", "u43");
//        idsManager.updateUserIdentificationFile();

        //THIS CODE TEST HOW TO COPY AN ANONYMIZED VERSION OF A BIOTOUCH DATASET
        BiotouchDatasetImageConverter dbConverter = new BiotouchDatasetImageConverter("d:\\test\\datasetTest", "d:\\test\\datasetTestAnonimo");
        dbConverter.copyFolderAnonimized(dbConverter.getDbFolderPath(),dbConverter.getNewDbFolderPath());


        
    }
}

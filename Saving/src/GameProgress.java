import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//import java.lang.String;
public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public void saveGame(GameProgress gameObj, String filePath) {
        try (ObjectOutputStream objStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objStream.writeObject(gameObj);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    public static void zipFiles(String zipPath, String[] fileArrayForZip) {
//        int entryNum = 1;
//        for (String pathFile : fileArrayForZip) {
//            System.out.println(pathFile);
//            String fileName = pathFile.substring(pathFile.lastIndexOf("/") + 1);
//            System.out.println(fileName);
//            try(ZipOutputStream saveAsZip = new ZipOutputStream(new FileOutputStream(zipPath, true));
//                FileInputStream readFileToZip = new FileInputStream(pathFile)) {
//                saveAsZip.putNextEntry(new ZipEntry(fileName));
//                byte[] byteBuffer = new byte[readFileToZip.available()];
//                readFileToZip.read(byteBuffer);
//                for (byte b : byteBuffer) {
//                    System.out.print(b + " ");
//                }
//                System.out.println();
//                saveAsZip.write(byteBuffer);
//                saveAsZip.closeEntry();
//
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }

    public static void zipFiles(String zipPath, String[] fileArrayForZip) {
        try (ZipOutputStream saveAsZip = new ZipOutputStream(new FileOutputStream(zipPath, true))) {
            for (String pathFile : fileArrayForZip) {
//                System.out.println(pathFile);
//                String fileName = pathFile.substring(pathFile.lastIndexOf("/") + 1);
//                System.out.println(fileName);
                saveAsZip.putNextEntry(new ZipEntry(GameProgress.getFileNameFromPath(pathFile)));
                FileInputStream readFileToZip = new FileInputStream(pathFile);
                byte[] byteBuffer = new byte[readFileToZip.available()];
                readFileToZip.read(byteBuffer);
                for (byte b : byteBuffer) {
                    System.out.print(b + " ");
                }
                System.out.println();
                readFileToZip.close();
                saveAsZip.write(byteBuffer);
                saveAsZip.closeEntry();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFiles(String pathFile) {
        File fileDel = new File(pathFile);
        if(fileDel.delete()) {
            System.out.printf("File %s has been deleted\n", GameProgress.getFileNameFromPath(pathFile));
        }
    }

    public static String getFileNameFromPath(String pathFile) {
            System.out.println(pathFile);
            String fileName = pathFile.substring(pathFile.lastIndexOf("/") + 1);
            System.out.println(fileName);
            return fileName;
    }
}

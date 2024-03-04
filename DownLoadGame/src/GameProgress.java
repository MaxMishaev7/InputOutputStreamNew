import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.ArrayList;

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

    public static ArrayList<String> openZip(String pathToArchive, String pathForUnzip) {
        ArrayList<String> fileNames = new ArrayList<>();
        try(ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(pathToArchive)))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                System.out.println(name);
                fileNames.add(name);

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C://GAMES/savegames/" + name));
                int c;
                while ((c = zin.read()) != -1) {
                    bos.write(c);
                }
                bos.flush();
                bos.close();
            }
            zin.closeEntry();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fileNames;
    }

    public static GameProgress openProgress(String pathToFile) {
        try (ObjectInputStream objFromFile = new ObjectInputStream(new FileInputStream(pathToFile))) {
            return (GameProgress) objFromFile.readObject();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

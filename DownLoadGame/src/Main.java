import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ArrayList<String> fileNames = new ArrayList<>(GameProgress.openZip("C://GAMES/savegames/output.zip", "C://GAMES/savegames/"));
//        GameProgress.openZip("C://GAMES/savegames/output.zip", "C://GAMES/savegames/");
        for(String file : fileNames) {
            System.out.println(GameProgress.openProgress("C://GAMES/savegames/" + file));
        }





    }
}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        String[] folders = {"src",
                "src/main",
                "src/main/Main.java",
                "src/main/Utils.java",
                "src/test",
                "res",
                "res/drawables",
                "res/vectors",
                "res/icons",
                "savegames",
                "temp",
                "temp/temp.txt"};
        File games = new File("C://GAMES");
        if (games.exists()) {
            log.append(String.format("Папка %s существует\n", games.getAbsolutePath()));
            for (String partOfPath : folders) {
                if (partOfPath.contains(".java") || partOfPath.contains(".txt")) {
                    int ind = partOfPath.lastIndexOf("/");
                    var folder = partOfPath.substring(0, ind);
                    var fileName = partOfPath.substring(ind + 1);
                    File filePath = new File("C://GAMES/" + folder, fileName);
                    try {
                        filePath.createNewFile();
                        log.append(String.format("Добавлен файл %s в папку %s\n", fileName, folder));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    File path = new File("C://GAMES/" + partOfPath);
                    if (path.mkdir()) {
                        log.append(String.format("Папка %s создана\n", path.getAbsolutePath()));
                    } else {
                        log.append(String.format("Папка %s уже существует\n", path.getAbsolutePath()));
                    }
                }
            }
            String logStr = log.toString();
            System.out.println(logStr);
            try(FileWriter temp = new FileWriter("C://GAMES/temp/temp.txt")) {
              temp.write(logStr);
              temp.flush();
            } catch (IOException ioEx) {
                System.out.println(ioEx.getMessage());
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GameProgress game1 = new GameProgress(80, 2, 15, 23.5);
        GameProgress game2 = new GameProgress(45, 7, 10, 8.5);
        GameProgress game3 = new GameProgress(30, 3, 23, 1.5);

        String path = "C://GAMES/savegames/";

        game1.saveGame(game1, path + "save1.dat");
        game2.saveGame(game2, path + "save2.dat");
        game3.saveGame(game3, path + "save3.dat");

        String[] paths = {"C://GAMES/savegames/save1.dat",
                          "C://GAMES/savegames/save2.dat",
                          "C://GAMES/savegames/save3.dat"};

        GameProgress.zipFiles("C://GAMES/savegames/output.zip", paths);

        for (String pathToFile : paths) {
            GameProgress.deleteFiles(pathToFile);
        }

    }
}
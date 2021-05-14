package sokoban;

import sokoban.database.Database;
import sokoban.database.DatabaseException;
import sokoban.builder.FileBoardBuilder;
import sokoban.board.BuildException;
import sokoban.board.Board;
import java.io.PrintStream;
import java.util.Scanner;

public class Administrator {

    private static final PrintStream out = System.out;
    private static Database database;

    public static void main(String[] args) throws DatabaseException, BuildException {
        out.println("* Bonjour.");
        menuAdmin();
    }

    public Administrator() throws DatabaseException {
        database = new Database();
    }

    private static void menuAdmin() throws DatabaseException, BuildException {
        boolean loop = true;

        while (loop) {
            out.println("* Menu");
            out.println("1. Créer une nouvelle base de données");
            out.println("2. Liste des plateaux");
            out.println("3. Montrer un plateau");
            out.println("4. Ajouter un plateau depuis un fichier");
            out.println("5. Supprimer un plateau de la base [DANGEREUX]");
            out.println("q. Quitter.");
            out.print("? ");
            String command = readLine();
            switch (command) {
                case "1" ->
                    createNewBase();
                case "2" ->
                    displayAllBoards();
                case "3" ->
                    displayABoard();
                case "4" ->
                    addBoard();
                case "5" ->
                    removeBoard();
                case "q" -> {
                    out.println("Bye.");
                    loop = false;
                }
                default ->
                    out.println("-> commande inconnue '" + command + "'");
            }
        }
    }

    public static void displayAllBoards() throws DatabaseException {
        database.displayBoards();
    }

    private static void createNewBase() throws DatabaseException {
        /*database = new Database();
        database.remove("", false);*/
        database = new Database();
    }

    private static void addBoard() throws BuildException, DatabaseException {
        String absolutePath = System.getProperty("user.dir").replace('\\', '/') + "/datafiles/";

        out.println("Veuillez saisir le nom du fichier texte situé dans datafiles (exemple : 'nomdufichier.txt')");
        String boardFile = readLine();
        String path = absolutePath + boardFile;

        out.println("Veuillez nommer votre plateau : ");
        String name = readLine();
        FileBoardBuilder fileBoard = new FileBoardBuilder(path, name);
        Board board = fileBoard.build();

        out.println("Veuillez saisir l'id de ce plateau dans la base : ");
        String id = readLine();
        database.add(id, board);
    }

    private static void displayABoard() throws DatabaseException {
        database.displayBoards();
        out.println("Veuillez saisir l'id du plateau à afficher : ");
        String id = readLine();
        if (database.idExists(id)) {
            database.displayRows(id);
        } else {
            out.println("id introuvable.");
            displayABoard();
        }
    }

    private static void removeBoard() throws DatabaseException {
        database.displayBoards();
        out.println("Veuillez saisir l'id du plateau à supprimer : ");
        String id = readLine();
        if (database.idExists(id)) {
            database.remove(id, true);
            out.println("succès.");
        } else {
            out.println("id introuvable.");
            removeBoard();
        }
    }

    public static Board getBoard() throws DatabaseException, BuildException {
        out.println("Veuillez saisir l'id du plateau de jeu : ");
        String id = readLine();
        Board board;

        if (database.idExists(id)) {
            board = database.get(id);
        } else {
            out.println("id introuvable.");
            board = getBoard();
        }
        return board;
    }

    /**
     * Lire l'entrée
     *
     * @return retourner l'entrée
     */
    private static String readLine() {
        return new Scanner(System.in).nextLine().trim();
    }
}

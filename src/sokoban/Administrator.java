package sokoban;

import java.io.PrintStream;
import java.util.Scanner;

public class Administrator {

    private static final PrintStream out = System.out;
    private static Database database;

    public static void main(String[] args) throws DatabaseException, BuildException {
        out.println("* Bonjour.");
        menu();
    }

    private static void menu() throws DatabaseException, BuildException {
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
                case "1":
                    database = new Database("sokoban.sqlite3");
                    break;
                case "2":
                    database.displayBoards();
                    break;
                case "3":
                    displayBoard();
                    break;
                case "4":
                    addBoard();
                    break;
                case "5":
                    removeBoard();
                    break;
                case "q":
                    out.println("-> Bye.");
                    loop = false;
                    break;
                default:
                    out.println("-> commande inconnue '" + command + "'");
                    break;
            }
        }
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

        out.println("Veuillez saisir le numéro id de ce plateau dans la base : ");
        String id = readLine();
        database.add(id, board);
    }

    private static void displayBoard() throws DatabaseException {
        out.println("Veuillez saisir l'id du plateau à afficher : ");
        String id = readLine();
        database.displayRows(id);
    }

    private static void removeBoard() throws DatabaseException {
        database.displayBoards();
        out.println("Veuillez saisir l'id du plateau à supprimer : ");
        String id = readLine();
        database.remove(id);
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

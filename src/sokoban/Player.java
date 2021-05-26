package sokoban;

import sokoban.database.DatabaseException;
import sokoban.board.BuildException;
import sokoban.board.Board;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Classe main Player
 *
 * @author Ayman KACHMAR
 */
public class Player {

    private static final PrintStream out = System.out;
    private static boolean playing = true;
    private static Board board;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BuildException, DatabaseException {
        System.out.println("\n* Bienvenue sur Sokoban.");
        System.out.println("Saisissez 'q' pour quitter à tout moment.\n");

        menuPlayer();
    }

    /**
     * Menu principal pour une partie
     * 
     * @throws DatabaseException
     * @throws BuildException 
     */
    private static void menuPlayer() throws DatabaseException, BuildException {
        boolean loop = true;
        final Administrator admin = new Administrator();

        while (loop) {
            out.println("* Menu");
            out.println("1. Sélectionner un plateau depuis la base.");
            out.println("2. Sélectionner un plateau depuis un fichier.");
            out.println("q. Quitter.");
            out.print("? ");
            String command = readLine();
            switch (command) {
                case "1" -> {
                    admin.displayAllBoards();
                    board = admin.getBoard();
                    game();
                    loop = false;
                }
                case "2" -> {
                    board = Administrator.requestBoard();
                    game();
                    loop = false;
                }
                case "q" -> {
                    out.println("-> Bye.");
                    loop = false;
                }
                default ->
                    out.println("-> commande inconnue '" + command + "'");
            }
        }
    }

    /**
     * Une partie
     */
    private static void game() throws BuildException {
        boolean victory = false;
        boolean turn = true;

        while (playing && !victory) {
            victory = board.victory();
            if (turn) {
                board.display();
                turn = false;
            } else {
                refreshBoard(board);
                turn = true;
            }
        }
    }

    /**
     * Actualiser le plateau après les coups joués
     *
     * @param b le plateau
     */
    private static void refreshBoard(Board b) {
        System.out.println("Saisissez un mouvement (L,R,U,D) : ");
        String entry = readLine();

        if (possibleEntry(entry)) {
            b.refreshPositions(entry.toUpperCase());
        } else if (entry.equals("q")) {
            playing = false;
            System.out.println("Bye.");
        } else {
            System.out.println("Aucun coup joué, réessayez.\n");
        }
    }

    /**
     * Vérifier que l'utilisateur peut uniquement entrer "L,R,U,D" et les
     * minuscules
     *
     * @param entry l'entrée
     * @return retourner vrai ss'il entre ceci
     */
    private static boolean possibleEntry(String entry) {
        boolean possible = true;
        int i = 0;

        while (i < entry.length() && possible) {
            possible = possible && "LRUDlrud".contains("" + entry.charAt(i));
            i++;
        }
        return possible;
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

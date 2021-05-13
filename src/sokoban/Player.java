import java.util.Scanner;

/**
 * Classe main
 *
 * @author Ayman KACHMAR
 */
public class Player {

    private static boolean playing = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\n* Bienvenue sur Sokoban.");
        System.out.println("Saisissez 'q' pour quitter à tout moment.\n");
        game();
    }

    /**
     * Le jeu
     */
    private static void game() {
        var builder = new TextBoardBuilder("A Simple Board");
        builder.addRow("# # # # # # # # # #");
        builder.addRow("# x . x # . . . . #");
        builder.addRow("# . . . C C . P . #");
        builder.addRow("# . . . . . . . . #");
        builder.addRow("# # # # # # # # # #");
        Board b1 = builder.createBoard();

        /*Board b = new Board("test", 6, 8);
        initBoard(b);*/
        boolean victory = false;

        while (playing && !victory) {
            victory = b1.victory();
            b1.display();
            refreshBoard(b1);
        }
    }

    /**
     * Initialiser un plateau
     *
     * @param b le plateau
     */
    private static void initBoard(Board b) {
        b.addTarget(1, 0);
        // b.addHorizontalWall(0, 0, 2);
        b.addHorizontalWall(3, 0, 3);
        b.addVerticalWall(2, 1, 3);
        b.addVerticalWall(1, 5 - 1, 5 - 1);
        b.setMyPos(0, 1);
        b.addBox(3, 3);
        b.addBox(2, 3);
        // b.addBox(1, 3);
        b.addTarget(4, 2);
        b.addTarget(4, 3);
        b.addTarget(4, 4);
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
            b.refreshPositions(entry);
            b.setMyPos(b.getMyPosition().getRow(), b.getMyPosition().getColumn());
        } else if (entry.equals("q")) {
            playing = false;
            System.out.println("Bye.");
        } else {
            System.out.println("Aucun coup joué, réessayez.\n");
        }
    }

    /**
     * Vérifier que l'utilisateur peut uniquement entrer "L,R,U,D"
     *
     * @param entry l'entrée
     * @return retourner vrai ss'il entre ceci
     */
    private static boolean possibleEntry(String entry) {
        boolean possible = true;
        int i = 0;

        while (i < entry.length() && possible) {
            possible = possible && "LRUD".contains("" + entry.charAt(i));
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

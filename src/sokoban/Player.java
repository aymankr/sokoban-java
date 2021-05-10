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
        Board b = new Board("test", 8, 8);
        initBoard(b);
        boolean turn = true;
        boolean victory = false;

        while (playing && !victory) {
            victory = b.noVictory();

            if (turn) {
                b.display();
                turn = false;
            } else {
                refreshBoard(b);
                turn = true;
            }
        }
    }

    /**
     * Initialiser un plateau
     *
     * @param b le plateau
     */
    private static void initBoard(Board b) {
        b.addHorizontalWall(0, 0, 2);
        b.addHorizontalWall(b.getHeight() - 1, 0, b.getWidth());
        b.addVerticalWall(2, 1, 3);
        b.addVerticalWall(1, b.getWidth() - 1, b.getHeight() - 1);
        b.addBox(3, 3);
        b.addTarget(3, 4);
        b.setMyPos(5, 3);
        b.addBox(5, 5);
        b.addTarget(4, 3);
        b.addTarget(6, 3);
        b.addBox(6, 2);
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
        return entry != null && !entry.equals("") && entry.equals("U") || entry.equals("R") || entry.equals("L")
                || entry.equals("D");
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

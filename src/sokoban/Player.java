
import java.io.File;
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
        /*
         * TextBoardBuilder builder = new TextBoardBuilder("A Simple Board");
         * builder.addRow("# # # # # # # # #"); builder.addRow("# x . x # . . . .");
         * builder.addRow("# B . B . P . . ."); builder.addRow("# . . . . . . . .");
         * builder.addRow("# # # # # # # # #"); Board b1 = builder.build();
         */

        String path = System.getProperty("user.dir").replace('\\', '/') + "/datafiles/board1.txt";

        FileBoardBuilder builder = new FileBoardBuilder(path, "A Board");
        Board b1 = builder.build();

        boolean victory = false;
        boolean turn = true;

        while (playing && !victory) {
            victory = b1.victory();
            if (turn) {
                b1.display();
                turn = false;
            } else {
                refreshBoard(b1);
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

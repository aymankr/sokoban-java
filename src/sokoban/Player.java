import java.io.PrintStream;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author A
 */
public class Player {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        out.println("* Bienvenue sur Sokoban.\n");
        game();
    }

    public static void game() {
        boolean loop = true;

        Board b = new Board("test", 8, 8);
        initBoard(b);

        int n = 100;

        while (loop) {
            if (n % 2 == 0) {
                b.display();
                n--;
            } else if (n % 2 == 1) {
                refreshBoard(b);
                n--;
            }
        }
    }

    private static void initBoard(Board b) {
        b.addHorizontalWall(0, 0, b.getWidth());
        b.addHorizontalWall(b.getHeight() - 1, 0, b.getWidth());
        b.addVerticalWall(1, 0, b.getHeight() - 1);
        b.addVerticalWall(1, b.getWidth() - 1, b.getHeight() - 1);
        b.addBox(2, 1);
        b.addBox(2, 2);
        b.addTarget(3, 4);
        b.setMyPos(1, 1);
    }

    private static void refreshBoard(Board b) {
        String move = readLine();

        if (possibleMove(move)) {
            b.refreshMyPos(move);
            b.setMyPos(b.getMyPosition().getRow(), b.getMyPosition().getColumn());
        }
        else {
            out.println("Aucun coup joué.\n");
        }
    }

    private static boolean possibleMove(String move) {
        return move != null && move.equals("U") || move.equals("R") || move.equals("L") || move.equals("D");
    }

    private static String readLine() {
        return in.nextLine().trim();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author A
 */
public class Board {

    private String name;
    private int height;
    private int width;
    private final Box[][] board;

    public Board(String name, int height, int width) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.board = new Box[height][width];
        initBoard();
    }

    /**
     * Initialiser un plateau composé de cases
     *
     */
    private void initBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Coordinates c = new Coordinates(i, j);
                board[i][j] = new Box(c);
            }
        }
    }

    public void display() {
        System.out.println(name + "\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j].getNature() + " ");
            }
            System.out.println("");
        }
        System.out.println();

    }

    public void addHorizontalWall(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            board[x][y + i].setNature('#');
        }
    }

    public void addVerticalWall(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            board[x + i][y].setNature('#');
        }
    }

    public void addBox(int x, int y) {
        board[x][y].setNature('C');
    }

    public void addTarget(int x, int y) {
        board[x][y].setNature('x');
    }

    public void addCharacter(int x, int y) {
        board[x][y].setNature('P');
    }

    public void setPosition() {

    }

    public String getName() {
        return this.name;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}

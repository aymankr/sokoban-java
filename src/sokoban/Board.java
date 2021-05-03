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
    private final Case[][] board;
    


    public Board(String name, int height, int width) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.board = new Case[height][width];
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
                board[i][j] = new Case(c);
            }
        }
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j].getNature());
            }
            System.out.println("");
        }
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

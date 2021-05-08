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
    private Position myPosition;

    public Board(String name, int width, int height) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.board = new Case[width][height];
        initBoard();
    }

    /**
     * Initialiser un plateau composé de cases
     *
     */
    private void initBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Position c = new Position(i, j);
                board[i][j] = new Case(c);
            }
        }
    }

    public void display() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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

    public void setMyPos(int x, int y) {
        Case c = board[x][y];
        myPosition = c.getPos();
        c.setNature('P');
    }

    public void refreshPositions(String move) {
        board[myPosition.getRow()][myPosition.getColumn()].setNature('.');
        Direction d = Direction.dirCorrespond(move);
        int newRow = myPosition.getRow() + d.getDr();
        int newCol = myPosition.getColumn() + d.getDc();
        Position myNewPos = new Position(newRow, newCol);
        Position nextMyNewPos = new Position(newRow + d.getDr(), newCol + d.getDc());

        refreshPosBox(d, newRow, newCol, myNewPos, nextMyNewPos);

        if (myNewPos.isInBoard(this) && !board[newRow][newCol].isWall()) {
            myPosition = myNewPos;
        } else {
            System.out.println("Mouvement impossible.");
        }
    }

    public void refreshPosBox(Direction d, int newRow, int newCol, Position myNewPos, Position nextMyNewPos) {
        if (nextMyNewPos.isInBoard(this) && !board[nextMyNewPos.getRow()][nextMyNewPos.getColumn()].isWall() && board[newRow][newCol].isBox()) {
            board[newRow][newCol].setNature('.');
            board[nextMyNewPos.getRow()][nextMyNewPos.getColumn()].setNature('C');
        }
    }

    public Position getMyPosition() {
        return myPosition;
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

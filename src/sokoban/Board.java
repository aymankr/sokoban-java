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

    private final String name;
    private final int height;
    private final int width;
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
                System.out.print(board[i][j].displayCase() + " ");
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
        Direction d = Direction.dirCorrespond(move);
        int newRow = myPosition.getRow() + d.getDr();
        int newCol = myPosition.getColumn() + d.getDc();
        Position myNewPos = myPosition.nextPosition(d);
        Position nextMyNewPos = myNewPos.nextPosition(d);

        if (myNewPos.isInBoard(this) && !board[newRow][newCol].isWall() && !board[newRow][newCol].isBox()) {
            refreshMyPosition(myNewPos);
        } else if (myNewPos.isInBoard(this) && nextMyNewPos.isInBoard(this)
                && !board[nextMyNewPos.getRow()][nextMyNewPos.getColumn()].isWall() && board[newRow][newCol].isBox()) {
            refreshBoxPosition(newRow, newCol, nextMyNewPos);
            refreshMyPosition(myNewPos);
        } else {
            System.out.println("Mouvement impossible");
        }
    }

    public void refreshBoxPosition(int newRow, int newCol, Position nextMyNewPos) {
        board[newRow][newCol].setNature('.');
        board[nextMyNewPos.getRow()][nextMyNewPos.getColumn()].setNature('C');
    }

    public void refreshMyPosition(Position myNewPos) {
        board[myPosition.getRow()][myPosition.getColumn()].setNature('.');
        myPosition = myNewPos;
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

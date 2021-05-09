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

        Position myNewPos = myPosition.nextPosition(d);
        Position nextMyNewPos = myNewPos.nextPosition(d);

        if (myNewPos.isInBoard(this) && !getCase(myNewPos).isWall() && !getCase(myNewPos).isBox()) {
            refreshMyPosition(myNewPos);
        } else if (myNewPos.isInBoard(this) && nextMyNewPos.isInBoard(this) && !getCase(nextMyNewPos).isWall()
                && getCase(myNewPos).isBox()) {
            refreshBoxPosition(myNewPos, nextMyNewPos);
            refreshMyPosition(myNewPos);
        } else {
            System.out.println("Mouvement impossible.\n");
        }
    }

    public void refreshBoxPosition(Position myNewPos, Position nextMyNewPos) {
        getCase(myNewPos).setNature('.');
        getCase(nextMyNewPos).setNature('C');
    }

    public void refreshMyPosition(Position myNewPos) {
        getCase(myPosition).setNature('.');
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

    public Case getCase(Position p) {
        return board[p.getRow()][p.getColumn()];
    }
}

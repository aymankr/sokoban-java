

import java.util.HashSet;

/**
 * Constructeur d'un plateau
 *
 * @author Ayman KACHMAR
 */
public class Board {

    private final String name;
    private final int height;
    private final int width;
    private final Case[][] board;
    private Position myPosition;
    private static final HashSet<Case> listBoxes = new HashSet<Case>();

    /**
     * Constructeur d'un plateau
     *
     * @param name son nom
     * @param width son largeur
     * @param height sa longueur
     */
    public Board(String name, int width, int height) {
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
                Position c = new Position(i, j);
                board[i][j] = new Case(c);
            }
        }
    }

    /**
     * Afficher le plateau
     */
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j].getCase() + " ");
            }
            System.out.println("");
        }
        System.out.println();
    }

    /**
     * Ajouter horizontalement des murs
     *
     * @param x abscisse
     * @param y ordonnée
     * @param length longueur
     */
    public void addHorizontalWall(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            board[x][y + i].setCar('#', false);
        }
    }

    /**
     * Ajouter verticalement des murs
     *
     * @param x abscisse
     * @param y ordonnée
     * @param length longueur
     */
    public void addVerticalWall(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            board[x + i][y].setCar('#', false);
        }
    }

    /**
     * Ajouter une caisse
     *
     * @param x abscisse
     * @param y ordonée
     */
    public void addBox(int x, int y) {
        board[x][y].setCar('B', false);
        listBoxes.add(board[x][y]);
    }

    /**
     * Ajouter une cible
     *
     * @param x abscisse
     * @param y ordonée
     */
    public void addTarget(int x, int y) {
        board[x][y].setCar('x', false);
        board[x][y].setIsTarget();
    }

    /**
     * Choisir la position de notre joueur
     *
     * @param x abscisse
     * @param y ordonée
     */
    public void setMyPos(int x, int y) {
        Case c = board[x][y];
        myPosition = c.getPos();
        c.setCar('P', false);
    }

    /**
     * Actualiser les positions du plateau en vérifiant qu'il n'y a pas de mur
     * en face et que l'on reste dans le plateau
     *
     * @param entry
     */
    public void refreshPositions(String entry) {
        for (int i = 0; i < entry.length(); i++) {
            Direction dir = Direction.dirCorrespond(entry.charAt(i));
            Position myNewPos = myPosition.nextPosition(dir);
            Position nextMyNewPos = myNewPos.nextPosition(dir);

            if (myNewPos.isInBoard(this) && !getCase(myNewPos).isWall() && !getCase(myNewPos).isBox()) {
                refreshMyPosition(myNewPos);
            } else if (myNewPos.isInBoard(this) && nextMyNewPos.isInBoard(this) && !getCase(nextMyNewPos).isWall()
                    && getCase(myNewPos).isBox() && !getCase(nextMyNewPos).isBox()) {
                refreshBoxPosition(myNewPos, nextMyNewPos, dir);
                refreshMyPosition(myNewPos);
            }
        }
    }

    /**
     * Actualiser la position d'une caisse
     *
     * @param oldPos ancienne position
     * @param newPos nouvelle position
     */
    private void refreshBoxPosition(Position oldPos, Position newPos, Direction d) {
        getCase(newPos).setCar('B', false);
        listBoxes.remove(getCase(oldPos));
        listBoxes.add(getCase(newPos));
    }

    /**
     * Actualiser notre position
     *
     * @param myNewPos nouvelle position
     */
    private void refreshMyPosition(Position myNewPos) {
        getCase(myPosition).setCar('.', true);
        myPosition = myNewPos;
    }

    /**
     * Récolter notre position
     *
     * @return retourner celle-ci
     */
    public Position getMyPosition() {
        return myPosition;
    }

    /**
     * Récolter le nom du plateau
     *
     * @return retourner son nom
     */
    public String getName() {
        return this.name;
    }

    /**
     * Récolter la longueur
     *
     * @return retourner cette longueur
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Récolter la largeur
     *
     * @return retourner ceci
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Récolter la case d'une position
     *
     * @param p la position
     * @return retourner la case
     */
    public Case getCase(Position p) {
        return board[p.getRow()][p.getColumn()];
    }

    public boolean victory() {
        boolean b = true;

        for (Case c : listBoxes) {
            b = b && c.isATarget();
        }

        if (b) {
            System.out.println("Victoire !");
        }
        return b;
    }
}

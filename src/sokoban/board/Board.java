package sokoban.board;

import java.util.HashSet;

/**
 * Classe d'un plateau (Board)
 *
 * @author Ayman KACHMAR
 */
public class Board {

    private final String name;
    private final int height;
    private final int width;
    private final Cell[][] board;
    private Position myPosition;
    private static final HashSet<Cell> listBoxes = new HashSet<Cell>();

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
        this.board = new Cell[height][width];
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
                board[i][j] = new Cell(c);
            }
        }
    }

    /**
     * Afficher le plateau
     */
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j].getCell() + " ");
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
        Cell c = board[x][y];
        myPosition = c.getPos();
        c.setCar('P', false);
    }

    /**
     * Actualiser les positions du plateau en vérifiant qu'il n'y a pas de mur
     * en face et que l'on reste dans le plateau
     *
     * @param entry entrée de l'utilisateur
     */
    public void refreshPositions(String entry) {
        for (int i = 0; i < entry.length(); i++) {
            Direction dir = Direction.dirCorrespond(entry.charAt(i));
            Position myNewPos = myPosition.nextPosition(dir);
            Position nextMyNewPos = myNewPos.nextPosition(dir);

            if (myNewPos.isInBoard(this) && !getCell(myNewPos).isWall() && !getCell(myNewPos).isBox()) {
                refreshMyPosition(myNewPos);
            } else if (myNewPos.isInBoard(this) && nextMyNewPos.isInBoard(this) && !getCell(nextMyNewPos).isWall()
                    && getCell(myNewPos).isBox() && !getCell(nextMyNewPos).isBox()) {
                refreshBoxPosition(myNewPos, nextMyNewPos, dir);
                refreshMyPosition(myNewPos);
            }
            setMyPos(getMyPosition().getRow(), getMyPosition().getColumn());
        }
    }

    /**
     * Actualiser la position d'une caisse
     *
     * @param oldPos ancienne position
     * @param newPos nouvelle position
     */
    private void refreshBoxPosition(Position oldPos, Position newPos, Direction d) {
        getCell(newPos).setCar('B', false);
        listBoxes.remove(getCell(oldPos));
        listBoxes.add(getCell(newPos));
    }

    /**
     * Actualiser notre position
     *
     * @param myNewPos nouvelle position
     */
    private void refreshMyPosition(Position myNewPos) {
        getCell(myPosition).setCar('.', true);
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
    public Cell getCell(Position p) {
        return board[p.getRow()][p.getColumn()];
    }

    /**
     * Détecter la victoire si les caisses sont sur des cases de type Target
     * @return retourner vrai ss'il y a victoire
     */
    public boolean victory() {
        boolean b = true;

        for (Cell c : listBoxes) {
            b = b && c.isATarget();
        }

        if (b) {
            System.out.println("Victoire !");
        }
        return b;
    }

    /**
     * Constituer un tableau de String contenant chaque ligne du plateau
     * @return retourner le tableau
     */
    public String[] getRowsBoard() {
        String s = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s += board[i][j].getCell() + " ";
            }
            s += "\n";
        }

        return s.split("\n");
    }
}


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

    /**
     * Afficher le plateau
     */
    public void display() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(board[i][j].displayCase() + " ");
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
            board[x][y + i].setNature('#');
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
            board[x + i][y].setNature('#');
        }
    }

    /**
     * Ajouter une caisse
     *
     * @param x abscisse
     * @param y ordonée
     */
    public void addBox(int x, int y) {
        board[x][y].setNature('C');
    }

    /**
     * Ajouter une cible
     *
     * @param x abscisse
     * @param y ordonée
     */
    public void addTarget(int x, int y) {
        board[x][y].setNature('x');
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
        c.setNature('P');
    }

    /**
     * Actualiser les positions du plateau en vérifiant qu'il n'y a pas de mur
     * et que la dimension du plateau est respectée
     *
     * @param move entrée du joueur
     */
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

    /**
     * Actualiser la position d'une caisse
     *
     * @param oldPos ancienne position
     * @param newPos nouvelle position
     */
    private void refreshBoxPosition(Position oldPos, Position newPos) {
        getCase(oldPos).setNature('.');
        getCase(newPos).setNature('C');
    }

    /**
     * Actualiser notre position
     *
     * @param myNewPos nouvelle position
     */
    private void refreshMyPosition(Position myNewPos) {
        getCase(myPosition).setNature('.');
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
    private Case getCase(Position p) {
        return board[p.getRow()][p.getColumn()];
    }
}

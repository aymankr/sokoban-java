
/**
 * Constructeur d'une position
 *
 * @author Ayman KACHMAR
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructeur d'un Coordonnees
     *
     * @param numRow numero de ligne
     * @param numCol numero de la colonne
     */
    public Position(int numRow, int numCol) {
        row = numRow;
        column = numCol;
    }

    /**
     * Indique si la coordonnée est dans le plateau
     *
     * @param b le plateau
     * @return retourne vrai ssi les coordonnées sont dans le plateau
     */
    public boolean isInBoard(Board b) {
        return this.row >= 0 && this.column >= 0 && this.row <= b.getHeight() - 1 && this.column <= b.getWidth() - 1;
    }

    /**
     * Retourner la ligne de cette position
     *
     * @return retourner cette ligne
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retourner la colonne
     *
     * @return retourner cette colonne
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Récolter la position suivante selon la direction
     *
     * @param d la direction
     * @return retourner cette position
     */
    public Position nextPosition(Direction d) {
        return new Position(row + d.getDr(), column + d.getDc());
    }
}

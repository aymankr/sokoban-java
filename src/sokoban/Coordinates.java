public class Coordinates {

    private final int row;
    private final int column;

    /**
     * Constructeur d'un Coordonnees
     *
     * @param numrow numéro de ligne
     * @param numcolumn numéro de colonne
     */
    public Coordinates(int numRow, int numCol) {
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
        return this.row >= 0 && this.column >= 0 && this.row <= b.getHeight()
                && this.column <= b.getWidth();
    }
}
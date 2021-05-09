
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructeur d'un Coordonnees
     *
     * @param numRow
     * @param numCol
     * @param numrow numéro de ligne
     * @param numcolumn numéro de colonne
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
        return this.row >= 0 && this.column >= 0 && this.row <= b.getHeight()-1
                && this.column <= b.getWidth() -1;
    }

    public boolean isInExtremity(Board b) {
        return row == 0 || row == b.getHeight()-1 || column == 0 || column == b.getWidth()-1;

    }

    public int getRow() {
        return this.row;
    }


    public int getColumn() {
        return this.column;
    }

    public Position nextPosition(Direction d) {
        return new Position(row + d.getDr(), column + d.getDc());
    } 
}
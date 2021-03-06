package sokoban.board;
/**
 * Enumeration des quatre directions
 *
 * @author Ayman KACHMAR
 */
public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

    private final int dr;
    private final int dc;

    /**
     * Constructeur d'une direction
     *
     * @param dc décalage sur la colonne
     * @param dr sur la ligne
     */
    private Direction(int dc, int dr) {
        this.dc = dc;
        this.dr = dr;
    }

    /**
     * Récolter la direction choisie
     *
     * @param c
     * @return retourner la direction
     */
    public static Direction dirCorrespond(char c) {
        Direction d = null;
        switch (c) {
            case 'L' -> d = LEFT;
            case 'R' -> d = RIGHT;
            case 'U' -> d = UP;
            case 'D' -> d = DOWN;
        }
        return d;
    }

    /**
     * Récolter le facteur de décalage de ligne
     *
     * @return retourner le facteur
     */
    public int getDr() {
        return dr;
    }

    /**
     * Récolter le facteur de décalage de colonne
     *
     * @return retourner le facteur
     */
    public int getDc() {
        return dc;
    }
}

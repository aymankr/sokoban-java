
/**
 * Constructeur d'une case
 *
 * @author Ayman KACHMAR
 */
public class Case {

    private final Position pos;
    private Nature nature;

    /**
     * Enumeration des cinq natures possibles
     */
    private enum Nature {
        NONE, BOX, WALL, TARGET, MYPOS
    };

    /**
     * Constructeur d'une case
     *
     * @param pos sa position
     */
    public Case(Position pos) {
        this.pos = pos;
        nature = Nature.NONE;
    }

    /**
     * Récolter le caractère de la case en fonction de sa nature
     *
     * @return retourner le caractère
     */
    public char displayCase() {
        char c = ' ';
        switch (nature) {
            case NONE -> c = '.';
            case WALL -> c = '#';
            case BOX -> c = 'C';
            case TARGET -> c = 'x';
            case MYPOS -> c = 'P';
        }
        return c;
    }

    /**
     * Modifier la nature de la case en fonction du caractère d'entrée
     *
     * @param c le caractère
     */
    public void setNature(char c) {
        switch (c) {
            case '#' -> nature = Nature.WALL;
            case 'C' -> nature = Nature.BOX;
            case 'P' -> nature = Nature.MYPOS;
            case 'x' -> nature = Nature.TARGET;
            case '.' -> nature = Nature.NONE;
        }
    }

    /**
     * Vérifier si cette case est un mur
     *
     * @return retourner vrai ssi c'est un mur
     */
    public boolean isWall() {
        return nature.equals(Nature.WALL);
    }

    /**
     * Vérifier si cette case est une caisse
     *
     * @return retourner vrai ssi c'est une caisse
     */
    public boolean isBox() {
        return nature.equals(Nature.BOX);
    }

    /**
     * Récolter la position de la case
     *
     * @return retourner la position
     */
    public Position getPos() {
        return pos;
    }
}

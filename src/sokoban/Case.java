
/**
 * Constructeur d'une case
 *
 * @author Ayman KACHMAR
 */
public class Case {

    private final Position pos;
    private char object;
    private boolean isTarget;

    /**
     * Constructeur d'une case
     *
     * @param pos sa position
     */
    public Case(Position pos) {
        this.pos = pos;
        this.object = '.';
        this.isTarget = false;
    }

    /**
     * Récolter le caractère de la case en fonction de sa nature
     *
     * @return retourner le caractère
     */
    public char displayCase() {
        return object;
    }

    /**
     * Modifier la nature de la case en fonction du caractère d'entrée
     *
     * @param c le caractère
     */
    public void setCar(char c, boolean byDefault) {
        if (byDefault && isTarget) {
            object = 'x';
        } else {
            object = c;
        }
    }

    /**
     * Vérifier si cette case est une caisse
     *
     * @return retourner vrai ssi c'est une caisse
     */
    public boolean isBox() {
        return object == 'B';
    }

    /**
     * Vérifier si cette case est une caisse
     *
     * @return retourner vrai ssi c'est une caisse
     */
    public boolean isWall() {
        return object == '#';
    }

    /**
     * Vérifier si cette case est une caisse
     *
     * @return retourner vrai ssi c'est une caisse
     */
    public boolean isATarget() {
        return object == 'x' || isTarget;
    }

    /**
     * Récolter la position de la case
     *
     * @return retourner la position
     */
    public Position getPos() {
        return pos;
    }

    public void setIsTarget() {
        isTarget = true;
    }

}

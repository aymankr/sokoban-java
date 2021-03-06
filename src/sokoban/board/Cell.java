package sokoban.board;

/**
 * Classe case
 *
 * @author Ayman KACHMAR
 */
public class Cell {

    private final Position pos;
    private char object; // objet correspondant à la case
    private boolean isTarget;

    /**
     * Constructeur d'une case
     *
     * @param pos sa position
     */
    public Cell(Position pos) {
        this.pos = pos;
        this.object = '.';
        this.isTarget = false;
    }

    /**
     * Récolter le caractère de la case en fonction de sa nature
     *
     * @return retourner le caractère
     */
    public char getCell() {
        return object;
    }

    /**
     * Modifier la nature de la case en fonction du caractère d'entrée
     *
     * @param c le caractère
     * @param byDefault
     */
    public void setCar(char c, boolean byDefault) {
        object = c;
        if (byDefault && isTarget) {
            object = 'x';
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
    public boolean isMyPos() {
        return object == 'P';
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

    /**
     * Mettre l'attribut isTarget à vrai pour cette case
     */
    public void setIsTarget() {
        isTarget = true;
    }

}

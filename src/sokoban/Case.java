public class Case {


    private Position pos;
    private char nature = '.';
    private boolean isWall = false;

    public Case(Position pos) {
        this.pos = pos;
    }

    public void setNature(char nature) {
        this.nature = nature;
        if (nature == '#') {
            isWall = true;
        }
        else {
            isWall = false;
        }
    }

    public char getNature() {
        return this.nature;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isWall() {
        return isWall;
    }
}

public class Case {


    private final Coordinates coord;
    private char nature = '.';


    public Case(Coordinates coord) {
        this.coord = coord;
    }

    public void setNature(char nature) {
        this.nature = nature;
    }

    public char getNature() {
        return this.nature;
    }
}

public class Box {


    private final Coordinates coord;
    private char nature = '.';

    public Box(Coordinates coord) {
        this.coord = coord;
    }

    public void setNature(char nature) {
        this.nature = nature;
    }

    public char getNature() {
        return this.nature;
    }
}


public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

    private final int dr;
    private final int dc;

    private Direction(int dc, int dr) {
        this.dr = dr;
        this.dc = dc;
    }

    public static Direction dirCorrespond(String move) {
        Direction d = null;
        switch (move) {
            case "L" -> d = LEFT;
            case "R" -> d = RIGHT;
            case "U" -> d = UP;
            case "D" -> d = DOWN;
        }
        return d;
    }

    public int getDr() {
        return dr;
    }

    public int getDc() {
        return dc;
    }
}

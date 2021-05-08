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
            case "L":
                d = LEFT;
                break;
            case "R":
                d = RIGHT;
                break;
            case "U":
                d = UP;
                break;
            case "D":
                d = DOWN;
                break;
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


public class Case {

    private final Position pos;
    private Nature nature;

    private enum Nature {
        NONE, BOX, WALL, TARGET, MYPOS
    };

    public Case(Position pos) {
        this.pos = pos;
        nature = Nature.NONE;
    }

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

    public void setNature(char c) {
        switch (c) {
            case '#' -> nature = Nature.WALL;
            case 'C' -> nature = Nature.BOX;
            case 'P' -> nature = Nature.MYPOS;
            case 'x' -> nature = Nature.TARGET;
            case '.' -> nature = Nature.NONE;
        }
    }

    public boolean isWall() {
        return nature.equals(Nature.WALL);
    }

    public boolean isBox() {
        return nature.equals(Nature.BOX);
    }
    
    public Position getPos() {
        return pos;
    }
}

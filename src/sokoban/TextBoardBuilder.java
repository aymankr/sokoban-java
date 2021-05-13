public class TextBoardBuilder {
    private String name;
    private int width;
    private int height;
    private String textBoard;

    public TextBoardBuilder(String name) {
        this.name = name;
        this.textBoard = "";
    }

    public void addRow(String row) {
        int plus = 0;
        textBoard += row + "\n";
        if (row.length() % 2 == 1) {
            plus = 1;
        }
        width = (row.length() / 2) + plus;
        height++;
    }

    public Board createBoard() {
        Board board = new Board(name, width, height);

        String[] rows = textBoard.replace(" ", "").split("\n");
        int numRow = 0;

        for (String s : rows) {
            for (int i = 0; i < width; i++) {
                Position pos = new Position(numRow, i);
                board.getCase(pos).setCar(s.charAt(i), false);
            }
            numRow++;
        }
        return board;
    }
}

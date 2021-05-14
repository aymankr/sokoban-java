package sokoban.builder;

import sokoban.board.BuildException;
import sokoban.board.Board;

public class TextBoardBuilder implements BoardBuilder {

    private final String name;
    private int width;
    private int height;
    private String textBoard;

    public TextBoardBuilder(String name) {
        this.name = name;
        this.textBoard = "";
    }

    public void addRow(String row) {
        textBoard += row + "\n";
        height++;
    }
    
    @Override
    public Board build() throws BuildException {
        String[] rows = textBoard.replace(" ", "").split("\n");
        width = rows[0].length();
        Board board = new Board(name, width, height);
        int numRow = 0;

        try {
            for (String s : rows) {
                for (int i = 0; i < width; i++) {
                    char object = s.charAt(i);
                    switch (object) {
                        case '#' -> board.addHorizontalWall(numRow, i, 1);
                        case 'x' -> board.addTarget(numRow, i);
                        case 'B' -> board.addBox(numRow, i);
                        case 'P' -> board.setMyPos(numRow, i);
                    }
                }
                numRow++;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            throw new BuildException(ex.getMessage());
        }

        return board;
    }
}

package sokoban.builder;
import sokoban.board.BuildException;
import sokoban.board.Board;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileBoardBuilder implements BoardBuilder {

    private final String path;
    private final String boardName;

    public FileBoardBuilder(String path, String name) {
        this.path = path;
        this.boardName = name;
    }

    @Override
    public Board build() throws BuildException {

        TextBoardBuilder textBoard = new TextBoardBuilder(boardName);
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                textBoard.addRow(row);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier non trouvé : " + ex);
        }

        Board board = textBoard.build();
        return board;
    }
}
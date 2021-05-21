package sokoban.builder;

import sokoban.board.BuildException;
import sokoban.board.Board;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe d'un FileBoardBuilder
 *
 * @author Ayman KACHMAR
 */
public class FileBoardBuilder implements BoardBuilder {

    private final String path;
    private final String boardName;

    /**
     * Constructeur d'un FileBoardBuilder
     *
     * @param path chemin du fichier
     * @param name nom du plateau
     */
    public FileBoardBuilder(String path, String name) {
        this.path = path;
        this.boardName = name;
    }

    /**
     * Construire un plateau de type Board en fonction du fichier lu, en
     * utilisant un TextBoardBuilder et la méthode addRow pour ajouter chaque
     * ligne
     *
     * @return retourner le plateau
     * @throws BuildException
     */
    @Override
    public Board build() throws BuildException {

        TextBoardBuilder textBoard = new TextBoardBuilder(boardName);
        try ( Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                textBoard.addRow(row);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier non trouvé : " + ex);
        }

        Board board = textBoard.build();

        if (!textBoard.isAValidBoard()) {
            throw new BuildException("le plateau ne peut pas être construit, les règles ne sont pas respectées.");
        }
        return board;
    }
}

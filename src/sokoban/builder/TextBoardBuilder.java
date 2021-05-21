package sokoban.builder;

import sokoban.board.BuildException;
import sokoban.board.Board;

/**
 * Classe d'un TextBoardBuilder
 *
 * @author Ayman KACHMAR
 */
public class TextBoardBuilder implements BoardBuilder {

    private final String name;
    private int width;
    private int height;
    private String textBoard;
    private boolean isValidBoard = true;
    int nbTargets = 0;
    int nbBoxes = 0;
    int nbMyPos = 0;

    /**
     * Constructeur d'un TextBoardBuilder
     *
     * @param name
     */
    public TextBoardBuilder(String name) {
        this.name = name;
        this.textBoard = "";
    }

    /**
     * Ajouter des lignes à la chaine de caractère correspondant au plateau
     *
     * @param row
     */
    public void addRow(String row) {
        textBoard += row + "\n";
        height++;
    }

    /**
     * Construire un plateau en fonction des lignes ajoutées par la méthode
     * addRow et attribuer à chaque case son objet
     *
     * @return retourner le plateau
     * @throws BuildException
     */
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
                        case '#' ->
                            board.addHorizontalWall(numRow, i, 1);
                        case 'x' -> {
                            board.addTarget(numRow, i);
                            nbTargets++;
                        }
                        case 'B' -> {
                            board.addBox(numRow, i);
                            nbBoxes++;
                        }
                        case 'P' -> {
                            board.setMyPos(numRow, i);
                            nbMyPos++;
                        }
                        case '.' -> {
                        }
                        case ' ' -> {

                        }
                        default ->
                            isValidBoard = false;
                    }
                }
                numRow++;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            throw new BuildException("Erreur de dimension : " + ex.getMessage());
        }

        return board;
    }

    public boolean isAValidBoard() {
        return (width >= 3 && height >= 3 && nbBoxes == nbTargets && nbBoxes != 0 && nbTargets != 0 && nbMyPos == 1);
    }
}



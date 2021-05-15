package sokoban.builder;

import sokoban.board.BuildException;
import sokoban.board.Board;

/**
 * Interface BoardBuilder
 * @author Ayman KACHMAR
 */
public interface BoardBuilder {

    /**
     * Méthode build pour créer un plateau Board
     * @return retourner le plateau
     * @throws BuildException 
     */
    Board build() throws BuildException;
}

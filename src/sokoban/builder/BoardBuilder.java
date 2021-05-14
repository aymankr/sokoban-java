package sokoban.builder;

import sokoban.board.BuildException;
import sokoban.board.Board;

public interface BoardBuilder {

    Board build() throws BuildException;
}

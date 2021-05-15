
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sokoban.board.Board;
import sokoban.board.BuildException;
import sokoban.board.Position;
import sokoban.builder.TextBoardBuilder;

public class GameTest {

    @Test
    public void testGame() throws BuildException {
        TextBoardBuilder builder = new TextBoardBuilder("A Simple Board");
        builder.addRow("# # # # # # # # # #");
        builder.addRow("# x . x # . . . . #");
        builder.addRow("# B . B . . . . . #");
        builder.addRow("# P . . . . . . . #");
        builder.addRow("# # # # # # # # # #");
        Board b = builder.build();

        // sur cette position
        Position p1 = new Position(3, 1);
        assertTrue(p1.getColumn() == b.getMyPosition().getColumn());
        assertTrue(p1.getRow() == b.getMyPosition().getRow());

        // après "R"
        b.refreshPositions("R");
        p1 = new Position(3, 2);
        assertTrue(p1.getColumn() == b.getMyPosition().getColumn());
        assertTrue(p1.getRow() == b.getMyPosition().getRow());

        // mouvements n'amenant pas à la victoire
        b.refreshPositions("LRRUDLL");
        assertFalse(b.victory());
        
        // à la victoire
        b.refreshPositions("UUDDRRUU");
        assertTrue(b.victory());
    }
}

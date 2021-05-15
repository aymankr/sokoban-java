
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sokoban.board.Board;
import sokoban.board.BuildException;
import sokoban.builder.FileBoardBuilder;
import sokoban.builder.TextBoardBuilder;

public class FileTextBoardBuilder {

    @Test
    public void testTextBuilderFileBuilder() throws BuildException {
        TextBoardBuilder builder1 = new TextBoardBuilder("A Simple Board");
        builder1.addRow("# # # # # # # # # #");
        builder1.addRow("# x . x # . . . . #");
        builder1.addRow("# . . . B . . P . #");
        builder1.addRow("# . . . . B . . . #");
        builder1.addRow("# # # # # # # # # #");
        Board b1 = builder1.build();

        String path = System.getProperty("user.dir").replace('\\', '/') + "/datafiles/simple.txt";
        FileBoardBuilder builder2 = new FileBoardBuilder(path, "A Simple Board");
        Board b2 = builder2.build();

        assertEquals(b1.getWidth(), b2.getWidth());
        assertEquals(b2.getHeight(), b2.getHeight());
        Assert.assertArrayEquals(b1.getRowsBoard(), b2.getRowsBoard()); // ont le même board en texte de type String
    }
}

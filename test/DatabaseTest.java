
import java.util.HashSet;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sokoban.board.Board;
import sokoban.board.BuildException;
import sokoban.builder.FileBoardBuilder;
import sokoban.database.Database;
import sokoban.database.DatabaseException;

public class DatabaseTest {

    @Test
    public void testDatabase() throws BuildException, DatabaseException {
        Database db = new Database();

        String path = System.getProperty("user.dir").replace('\\', '/') + "/datafiles/simple.txt";
        FileBoardBuilder builder = new FileBoardBuilder(path, "A Simple Board");
        Board b1 = builder.build();

        db.remove("", false); // tout supprimer
        db.add("1", b1);
        Board getB1 = db.get("1");
        Assert.assertArrayEquals(b1.getRowsBoard(), getB1.getRowsBoard()); // ont le même board
        db.remove("1", true);
        
        db.add("Moyen", b1);
        db.add("Facile", b1);
        db.add("Difficile", b1);
        HashSet<String> allIdsDb = db.getAllIDs();
        HashSet<String> allIdsTest = new HashSet<>();
        allIdsTest.add("Moyen");
        allIdsTest.add("Facile");
        allIdsTest.add("Difficile");
        
        assertEquals(allIdsDb, allIdsTest);
    }
}

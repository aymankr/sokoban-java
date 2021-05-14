package sokoban;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private static final PrintStream out = System.out;
    private final Connection connection;

    public Database() throws DatabaseException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sokoban.sqlite3");
            createDatas();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    private void createDatas() throws DatabaseException {
        try {
            String createBoardsSQL = "CREATE TABLE IF NOT EXISTS `boards`(" + " `board_id` text not null,"
                    + " `name` text not null," + " `nb_rows` integer not null," + " `nb_cols` integer not null,"
                    + " primary key (`board_id`))";

            String createRowsSQL = "CREATE TABLE IF NOT EXISTS `rows`(" + " `board_id` text not null,"
                    + " `row_num` integer not null," + " `description` text not null,"
                    + " primary key (`board_id`, `row_num`),"
                    + " foreign key (`board_id`) references `boards` (`board_id`))";

            Statement statement = connection.createStatement();
            statement.execute(createBoardsSQL);
            statement.execute(createRowsSQL);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public void add(String id, Board b) throws DatabaseException, BuildException {
        String insertBoardSQL = "INSERT INTO `boards` (board_id, name, nb_rows, nb_cols) VALUES(?, ?, ?, ?)";
        String insertRowSQL = "INSERT INTO `rows` (board_id, row_num, description) VALUES(?, ?, ?)";

        try {
            PreparedStatement insertBoard = connection.prepareStatement(insertBoardSQL);
            PreparedStatement insertRow = connection.prepareStatement(insertRowSQL);

            insertBoard.setString(1, id);

            insertBoard.setString(2, b.getName());
            insertBoard.setInt(3, b.getHeight());
            insertBoard.setInt(4, b.getWidth());
            insertBoard.executeUpdate();

            String[] rows = b.getRowsBoard();

            for (int i = 0; i < rows.length; i++) {
                insertRow.setString(1, id);
                insertRow.setInt(2, i);
                insertRow.setString(3, rows[i]);
                insertRow.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public void remove(String id) throws DatabaseException {
        String deleteBoardSQL = "DELETE FROM boards WHERE board_id = ?";
        String deleteRowsSQL = "DELETE FROM rows WHERE board_id = ?";

        try {
            PreparedStatement deleteBoard = connection.prepareStatement(deleteBoardSQL);
            PreparedStatement deleteRows = connection.prepareStatement(deleteRowsSQL);

            deleteBoard.setString(1, id);
            deleteRows.setString(1, id);
            deleteBoard.executeUpdate();
            deleteRows.executeUpdate();
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public Board get(String id) throws DatabaseException, BuildException {
        String getNameSQL = "SELECT name FROM boards WHERE board_id = ?";
        String getBoardSQL = "SELECT description FROM rows WHERE board_id = ?";
        TextBoardBuilder builder;

        try {
            PreparedStatement statementName = connection.prepareStatement(getNameSQL);
            statementName.setString(1, id);
            ResultSet selectName = statementName.executeQuery();

            PreparedStatement statementRows = connection.prepareStatement(getBoardSQL);
            statementRows.setString(1, id);
            ResultSet selectRows = statementRows.executeQuery();
            builder = new TextBoardBuilder(selectName.getString("name"));

            while (selectRows.next()) {
                builder.addRow(selectRows.getString("description"));
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
        return builder.build();
    }

    public void displayBoards() throws DatabaseException {
        out.println("BOARDS");
        String getAllBoardsSQL = "SELECT * FROM boards";

        try {
            ResultSet getAllBoards = connection.prepareStatement(getAllBoardsSQL).executeQuery();

            out.println("|-------------|---------------------------|---------|---------|");
            out.println("| board_id    | name                      | nb_rows | nb_cols |");
            out.println("|-------------|---------------------------|---------|---------+");

            while (getAllBoards.next()) {
                String id = getAllBoards.getString("board_id");
                String name = getAllBoards.getString("name");
                int numRows = getAllBoards.getInt("nb_rows");
                int numCols = getAllBoards.getInt("nb_cols");
                out.printf("| %-11s | %-25s | %-3d     | %-3d     |\n",
                        id, name, numRows, numCols);
            }
            out.println("|-------------|---------------------------|---------|---------|");

        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    public void displayRows(String boardId) throws DatabaseException {
        out.println("ROWS");
        String getAllRowsSQL = "SELECT * FROM rows WHERE board_id = " + boardId;

        try {
            ResultSet getAllRows = connection.prepareStatement(getAllRowsSQL).executeQuery();

            out.println("|-------------|-------------------------------------|");
            out.println("| board_id    | row_num | description               |");
            out.println("|-------------|-------------------------------------|");

            while (getAllRows.next()) {
                String id = getAllRows.getString("board_id");
                int numRow = getAllRows.getInt("row_num");
                String description = getAllRows.getString("description");

                out.printf("| %-11s | %-3d     | %-25s |\n",
                        id, numRow, description);
            }

            out.println("|-------------|-------------------------------------|");
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }
}

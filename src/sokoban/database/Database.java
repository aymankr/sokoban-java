package sokoban.database;

import sokoban.builder.TextBoardBuilder;
import sokoban.board.BuildException;
import sokoban.board.Board;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Constructeur d'une base
 *
 * @author Ayman KACHMAR
 */
public class Database {

    private static final PrintStream out = System.out;
    private final Connection connection;

    /**
     * Constructeur d'une base
     * @throws DatabaseException 
     */
    public Database() throws DatabaseException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sokoban.sqlite3");
            createDatas();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    /**
     * Création des tables
     * @throws DatabaseException 
     */
    private void createDatas() throws DatabaseException {
        try {
            String createBoardsSQL = "CREATE TABLE IF NOT EXISTS `boards`(" + " `board_id` text not null,"
                    + " `name` text not null," + " `nb_rows` integer not null," + " `nb_cols` integer not null,"
                    + " primary key (`board_id`))";

            String createRowsSQL = "CREATE TABLE IF NOT EXISTS `rows`(" + " `board_id` text not null,"
                    + " `row_num` integer not null," + " `description` text not null,"
                    + " primary key (`board_id`),"
                    + " foreign key (`board_id`) references `boards` (`board_id`))";

            Statement statement = connection.createStatement();
            statement.execute(createBoardsSQL);
            statement.execute(createRowsSQL);
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    /**
     * Ajouter un plateau de type Board en lui attribuant son id dans la base
     * @param id id du board
     * @param b le board
     * @throws DatabaseException
     * @throws BuildException 
     */
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

    /**
     * Supprimer un plateau de la base en fonction de l'id entré
     * @param id l'id
     * @param isDeletingOne vrai ss'il y a suppression d'un seul plateau
     * @throws DatabaseException 
     */
    public void remove(String id, boolean isDeletingOne) throws DatabaseException {
        String deleteFromIdSQL = "";
        if (isDeletingOne) {
            deleteFromIdSQL = "WHERE board_id = ?";
        }

        String deleteBoardSQL = "DELETE FROM boards " + deleteFromIdSQL;
        String deleteRowsSQL = "DELETE FROM rows " + deleteFromIdSQL;

        try {
            PreparedStatement deleteBoard = connection.prepareStatement(deleteBoardSQL);
            PreparedStatement deleteRows = connection.prepareStatement(deleteRowsSQL);

            if (isDeletingOne) {
                deleteBoard.setString(1, id);
                deleteRows.setString(1, id);
            }
            deleteBoard.executeUpdate();
            deleteRows.executeUpdate();
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    /**
     * Récupérer un plateau de la base en fonction de l'id entré 
     * @param id l'id
     * @return retourner le plateau
     * @throws DatabaseException
     * @throws BuildException 
     */
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
            builder = new TextBoardBuilder(selectName.getString(1));

            while (selectRows.next()) {
                builder.addRow(selectRows.getString(1));
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
        return builder.build();
    }

    /**
     * Afficher les plateaux de la base
     * @throws DatabaseException 
     */
    public void displayBoards() throws DatabaseException {
        out.println("BOARDS");
        String getAllBoardsSQL = "SELECT * FROM boards";

        try {
            ResultSet getAllBoards = connection.prepareStatement(getAllBoardsSQL).executeQuery();

            out.println("|-------------|---------------------------|---------|---------|");
            out.println("| board_id    | name                      | nb_rows | nb_cols |");
            out.println("|-------------|---------------------------|---------|---------+");

            while (getAllBoards.next()) {
                String id = getAllBoards.getString(1);
                String name = getAllBoards.getString(2);
                int numRows = getAllBoards.getInt(3);
                int numCols = getAllBoards.getInt(4);
                out.printf("| %-11s | %-25s | %-3d     | %-3d     |\n",
                        id, name, numRows, numCols);
            }
            out.println("|-------------|---------------------------|---------|---------|");

        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    /**
     * Afficher les lignes d'un board choisi correspondant à l'id entré
     * @param boardId l'id
     * @throws DatabaseException 
     */
    public void displayRows(String boardId) throws DatabaseException {
        out.println("ROWS");
        String getAllRowsSQL = "SELECT * FROM rows WHERE board_id LIKE '" + boardId + "'";

        try {
            ResultSet getAllRows = connection.prepareStatement(getAllRowsSQL).executeQuery();

            out.println("|-------------|-------------------------------------|");
            out.println("| board_id    | row_num | description               |");
            out.println("|-------------|-------------------------------------|");

            while (getAllRows.next()) {
                String id = getAllRows.getString(1);
                int numRow = getAllRows.getInt(2);
                String description = getAllRows.getString(3);

                out.printf("| %-11s | %-3d     | %-25s |\n",
                        id, numRow, description);
            }

            out.println("|-------------|-------------------------------------|");
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    /**
     * Récolter tous les id des boards de la base
     * @return retourner la collection
     * @throws DatabaseException 
     */
    public HashSet<String> getAllIDs() throws DatabaseException {
        String getIdsSQL = "SELECT board_id FROM boards";
        HashSet<String> allIds = new HashSet<>();

        try {
            ResultSet getIds = connection.prepareStatement(getIdsSQL).executeQuery();

            while (getIds.next()) {
                String id = getIds.getString(1);
                allIds.add(id);
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
        }
        return allIds;
    }
}

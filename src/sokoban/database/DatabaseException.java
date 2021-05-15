package sokoban.database;
/**
 * Classe d'exception pour la base
 *
 * @author Ayman KACHMAR
 */
public class DatabaseException extends Exception {

    /**
     * Constructeur d'un DatabaseException
     * @param message le message
     */
    public DatabaseException(String message) {
        super(message);
    }
}

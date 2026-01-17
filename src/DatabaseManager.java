import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private final String url = "jdbc:postgresql://localhost:5432/pet_adoption";
    private final String user = "postgres";
    private final String password = "Aseka2005";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
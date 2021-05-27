package coursemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Dibas Sigdel
 */
public class Database {

    private final static String className = "oracle.jdbc.driver.OracleDriver";
    private final static String url = "jdbc:mysql://localhost:3306/coursemanagement?serverTimezone=UTC";
    private final static String user = "root";
    private final static String password = "";
    private static Connection connection;

    /**
     * Returns the database connection.
     *
     * @return database connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
//                Class.forName(className);
                connection = DriverManager.getConnection(url, user, password);
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }
}


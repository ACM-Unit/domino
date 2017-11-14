package dbConnection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class which contain common methods for dao classes and get connection from connection pool
 */
public class DbConnection {

    /**
     * Get a connection to database
     * @return Connection object
     */

    private Logger LOGGER = Logger.getLogger(getClass());
    public Connection connection;
    public PreparedStatement stmt = null;
    public ResultSet rs = null;
    public Connection getConnection() {
        return connection;
    }

    /**
     * method for close connection, statement and result set
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception while close connection");
        }
    }
}
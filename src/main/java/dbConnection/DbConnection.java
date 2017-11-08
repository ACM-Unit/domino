package dbConnection;

import javax.sql.DataSource;
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
    public Connection connection = null;
    public PreparedStatement stmt = null;
    public ResultSet rs = null;
    public Connection getConnection() {
        ConnectionPool jdbcObj = new ConnectionPool();
        try {
            DataSource dataSource = jdbcObj.setUpPool();
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbcObj.printDbStatus();
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
            e.printStackTrace();
        }
    }
}
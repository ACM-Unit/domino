package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by viko0417 on 12/8/2017.
 */
public interface Dao {
    default void close(Connection connection, PreparedStatement stmt, ResultSet rs, Logger LOGGER) {
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

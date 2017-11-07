package dbConnection;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    /**
     * Get a connection to database
     * @return Connection object
     */
    public Connection getConnection()
    {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            in = new FileInputStream(classLoader.getResource("database.properties").getFile());
        props.load(in);
        in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConnectionPool jdbcObj = new ConnectionPool();
        try {
            DataSource dataSource = jdbcObj.setUpPool();
            jdbcObj.printDbStatus();
            dataSource.getConnection();
            jdbcObj.printDbStatus();
            Class.forName("com.mysql.jdbc.Driver");
            return dataSource.getConnection();
        } catch (ClassNotFoundException e) {
            System.out.println("driver sql not found");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
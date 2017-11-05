package dbConnection;

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
    public static Connection getConnection()
    {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("D:/MySites/domino/src/main/resources/database.properties");
        props.load(in);
        in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("jdbc:mysql://localhost/domino?user="+props.getProperty("user")+"&password="+props.getProperty("password")+"&characterEncoding=UTF-8");
            return DriverManager.getConnection("jdbc:mysql://localhost/domino?user="+props.getProperty("user")+"&password="+props.getProperty("password")+"&characterEncoding=UTF-8");
        } catch (ClassNotFoundException e) {
            System.out.println("driver sql not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
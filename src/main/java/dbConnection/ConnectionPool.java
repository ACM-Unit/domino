package dbConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 * Created by Viacheslav Koshchii on 11/7/2017.
 */
public class ConnectionPool {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/domino";
    private Logger LOGGER = Logger.getLogger(getClass());
    private static String JDBC_USER;
    private static String JDBC_PASS;

    private static GenericObjectPool gPool = null;

    public ConnectionPool() {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            in = new FileInputStream(classLoader.getResource("database.properties").getFile());
            props.load(in);
            in.close();
            JDBC_USER = props.getProperty("user");
            JDBC_PASS = props.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get DataSource from Connection pool
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public DataSource setUpPool() throws Exception {
        Class.forName(JDBC_DRIVER);
        gPool = new GenericObjectPool();
        gPool.setMaxActive(5);
        ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
        return new PoolingDataSource(gPool);
    }

    public GenericObjectPool getConnectionPool() {
        return gPool;
    }

    // This Method Is Used To Print The Connection Pool Status
    void printDbStatus() {
        LOGGER.info("Max.: " + getConnectionPool().getMaxActive() + "; Active: " + getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
    }

}
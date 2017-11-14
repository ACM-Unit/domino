package listeners;

import dbConnection.ConnectionPool;
import org.apache.log4j.Logger;
import services.ChainService;
import services.DominoService;
import services.MarketService;
import services.impl.ChainServiceImpl;
import services.impl.DominoServiceImpl;
import services.impl.MarketServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by viko0417 on 11/13/2017.
 */
public class Config implements ServletContextListener {
    private Logger LOGGER = Logger.getLogger(getClass());
    private static final String ATTRIBUTE_NAME = "config";
    private DataSource dataSource;
    private ChainService chainService;
    private DominoService dominoService;
    private MarketService marketService;
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        ConnectionPool jdbcObj = new ConnectionPool();
        try {
            dataSource = jdbcObj.setUpPool();
            chainService = new ChainServiceImpl(dataSource);
            dominoService = new DominoServiceImpl(dataSource);
            marketService = new MarketServiceImpl(dataSource);
            System.out.println("good");
        } catch (SQLException e) {
            LOGGER.error("SQL exception while creation pool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbcObj.printDbStatus();
        servletContext.setAttribute(ATTRIBUTE_NAME, this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public ChainService getChainService() {
        return chainService;
    }

    public void setChainService(ChainService chainService) {
        this.chainService = chainService;
    }

    public DominoService getDominoService() {
        return dominoService;
    }

    public void setDominoService(DominoService dominoService) {
        this.dominoService = dominoService;
    }

    public MarketService getMarketService() {
        return marketService;
    }

    public void setMarketService(MarketService marketService) {
        this.marketService = marketService;
    }

    public static Config getInstance(ServletContext servletContext) {
        return (Config) servletContext.getAttribute(ATTRIBUTE_NAME);
    }
}

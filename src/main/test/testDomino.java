import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.DominoDao;
import dao.MarketDao;
import dao.impl.ChainDaoImpl;
import dao.impl.DominoDaoImpl;
import dao.impl.MarketDaoImpl;
import dbConnection.ConnectionPool;
import entity.Domino;
import entity.Market;
import org.junit.Before;
import org.junit.Test;
import services.ChainService;
import services.DominoService;
import services.MarketService;
import services.impl.ChainServiceImpl;
import services.impl.DominoServiceImpl;
import services.impl.MarketServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Viacheslav Koshchii on 03.11.2017.
 */
public class testDomino {
    private DominoServiceImpl service;
    private DataSource dataSource;

    @Before
    public void preTest() throws SQLException {
        ConnectionPool jdbcObj = new ConnectionPool();
        try {
            dataSource = jdbcObj.setUpPool();
        } catch (SQLException e) {
            System.out.println("SQL exception while creation pool");
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbcObj.printDbStatus();
        service = new DominoServiceImpl(dataSource);


    }

    @Test
    public void testAlgorithm() throws TimeoutException {
        List<Domino> list = service.getByIds("5, 8, 11, 15, 25");
        Market market = new Market(list, "new");
        try {
            assert (market.AllChainsToString().equals("4:1, 1:1 ;\n" +
                    "1:4, 4:6 ;\n" +
                    "6:4, 4:0 ;\n" +
                    "1:4, 4:0 ;\n" +
                    "1:1, 1:4, 4:0 ;\n" +
                    "1:1, 1:4, 4:6 ;\n" +
                    "6:4, 4:1, 1:1 ;\n" +
                    "0:4, 4:1, 1:1 ;\n" +
                    "0:4, 4:6 ;"));
        } catch (TimeoutException e) {
            System.out.println("terminated");
        }
    }

    @Test
    public void testMarket() throws SQLException {
        DominoDao dao = new DominoDaoImpl(dataSource);
        List<Domino> listDomino = dao.getDominoesByIds("5, 8, 11, 15, 25");
        for (Domino domino : listDomino) {
            System.out.println(domino.toString());
        }
        Market market = null;
        MarketDao dao1 = new MarketDaoImpl(dataSource);
        ChainDao dao2 = new ChainDaoImpl(dataSource);
        try {
            market = new Market(listDomino, "new Market");
            dao1.insertMarket(market, "All");
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("name not unique");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        assert (dao1.getMarketByName("new Market").getMarket().size() != 0);
    }

    @Test
    public void testDeleteMarket() throws SQLException {
        MarketDao dao1 = new MarketDaoImpl(dataSource);
        ChainDao dao2 = new ChainDaoImpl(dataSource);
        System.out.println(dao1.getMarketByName("MyAllChains").getMarket().size() == 0 && dao2.getChainByName("MyAllChains").getChains().size() == 0);
        dao1.deleteMarket("MyAllChains");
        assert (dao1.getMarketByName("MyAllChains").getMarket().size() == 0 && dao2.getChainByName("MyAllChains").getChains().size() == 0);
    }

    @Test
    public void testRandom() throws SQLException {
        DominoServiceImpl service = new DominoServiceImpl(dataSource);
        for (Domino domino : service.getRandom(28)) {
            System.out.println(domino);
        }
    }
}

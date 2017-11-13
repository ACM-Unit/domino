package services.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.MarketDao;
import dao.impl.ChainDaoImpl;
import dao.impl.MarketDaoImpl;
import entity.Chain;
import entity.Domino;
import entity.Market;
import org.apache.log4j.Logger;
import services.MarketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Viacheslav Koshchii on 06.11.2017.
 */
public class MarketServiceImpl implements MarketService {
    private Logger LOGGER = Logger.getLogger(getClass());
    private MarketDao dao = new MarketDaoImpl();
    private ChainDao chainDao = new ChainDaoImpl();

    public List<String> getAllNames() {
        return dao.getAllMarketNames();
    }

    @Override
    public Market getMarketByName(String name) {
        return dao.getMarketByName(name);
    }

    public boolean saveMarketAndAllChains(Market market) {
        try {
            dao.insertMarket(market);
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        } catch (TimeoutException e) {
            LOGGER.error("Time out");
        }
        return true;
    }

    public boolean saveMarketAndLongestChains(Market market){

        try {
            dao.insertMarket(market);
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        } catch (TimeoutException e) {
            LOGGER.error("Time out");
        }
        return true;
    }
    public void delete(String name){
        dao.deleteMarket(name);
    }
}

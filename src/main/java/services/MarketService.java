package services;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.MarketDao;
import dao.impl.ChainDaoImpl;
import dao.impl.MarketDaoImpl;
import entity.Market;

import java.util.List;

/**
 * Created by Admin on 06.11.2017.
 */
public class MarketService {
    private MarketDao dao = new MarketDaoImpl();
    private ChainDao chainDao = new ChainDaoImpl();

    public List<String> getAllNames() {
        return dao.getAllMarketNames();
    }

    public boolean saveMarketAndAllChains(Market market) {
        try {
            dao.insertMarket(market);
            chainDao.insertChain(market.getAllChains(), market.getName());
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        }
        return true;
    }

    public boolean saveMarketAndLongestChains(Market market) throws MySQLIntegrityConstraintViolationException {

        if (dao.insertMarket(market) && chainDao.insertChain(market.getLongestMarkets(), market.getName())) {
            return true;
        }
        return false;
    }
}

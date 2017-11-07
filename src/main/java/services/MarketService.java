package services;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.MarketDao;
import dao.impl.ChainDaoImpl;
import dao.impl.MarketDaoImpl;
import entity.Chain;
import entity.Domino;
import entity.Market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viacheslav Koshchii on 06.11.2017.
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
            Chain chain = new Chain();
            Map<Integer, List<Domino>> map = new HashMap<>();
            chain.setMarket(market);
            List<List<Domino>> list = new ArrayList<>();
            list.addAll(market.getAllChains());
            for (int i=0; i<list.size(); i++){
                map.put(i, list.get(i));
            }
            chain.setChains(map);
            chainDao.insertChain(chain);
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        }
        return true;
    }

    public boolean saveMarketAndLongestChains(Market market){

        try {
            dao.insertMarket(market);
            Chain chain = new Chain();
            Map<Integer, List<Domino>> map = new HashMap<>();
            chain.setMarket(market);
            List<List<Domino>> list = new ArrayList<>();
            list.addAll(market.getLongestChains());
            for (int i=0; i<list.size(); i++){
                map.put(i, list.get(i));
            }
            chain.setChains(map);
            chainDao.insertChain(chain);
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        }
        return true;
    }
    public void delete(String name){
        dao.deleteMarket(name);
    }
}

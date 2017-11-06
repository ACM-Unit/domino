package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entity.Market;

import java.util.List;

/**
 * Created by Admin on 05.11.2017.
 */
public interface MarketDao {
    List<String> getAllMarketNames();
    Market getMarketByName(String name);
    boolean insertMarket(Market market) throws MySQLIntegrityConstraintViolationException;
    boolean deleteMarket(String[] names);
}

package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entity.Market;

import java.util.List;

/**
 * Class for work with sets of domino from Database
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public interface MarketDao {
    /**
     * method for get all names of  sets of domino which save in database
     * @return
     */
    List<String> getAllMarketNames();

    /**
     * method for get all sets of domino which have given name from Data Base
     * @param name
     * @return
     */
    Market getMarketByName(String name);

    /**
     *method for save  set of domino. This method throw exception
     * @param market
     * @return
     * @throws MySQLIntegrityConstraintViolationException
     */
    boolean insertMarket(Market market) throws MySQLIntegrityConstraintViolationException;
    /**
     * method for delete set of domino into database
     * @param name
     * @return
     */
    boolean deleteMarket(String name);
}

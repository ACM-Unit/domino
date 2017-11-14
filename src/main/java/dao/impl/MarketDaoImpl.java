package dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.MarketDao;
import dbConnection.DbConnection;
import entity.Chain;
import entity.Domino;
import entity.Market;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public class MarketDaoImpl extends DbConnection implements MarketDao {
    private Logger LOGGER = Logger.getLogger(getClass());

    public MarketDaoImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<String> getAllMarketNames() {
        getConnection();
        List<String> names = null;
        try {
            stmt = connection.prepareStatement("SELECT distinct name FROM market");
            rs = stmt.executeQuery();
            names = new ArrayList<>();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while getting all markets");
        } finally {
            close();
        }
        return names;
    }

    @Override
    public Market getMarketByName(String name) {
        getConnection();
        Market market = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino d, market m where d.id = m.domino  and m.name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            List<Domino> list = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId(rs.getInt("id"));
                list.add(domino);
            }

            market = new Market(list, name);
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while getting market by name");
        } finally {
            close();
        }
        return market;
    }

    @Override
    public boolean insertMarket(Market market) throws MySQLIntegrityConstraintViolationException, TimeoutException {
        Chain chain = new Chain();
        Map<Integer, List<Domino>> map = new HashMap<>();
        chain.setMarket(market);
        List<List<Domino>> list = new ArrayList<>();
        list.addAll(market.getAllChains());
        for (int i = 0; i < list.size(); i++) {
            map.put(i, list.get(i));
        }
        chain.setChains(map);
        StringBuilder idString = new StringBuilder("");
        for (int i = 0; i < market.getMarket().size(); i++) {
            if (i == market.getMarket().size() - 1) {
                idString.append(market.getMarket().get(i).getId());
            } else {
                idString.append(market.getMarket().get(i).getId()).append(",");
            }
        }
        getConnection();
        try {
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement("INSERT INTO `domino`.`market` SELECT '" + market.getName() + "', id FROM domino where id in (" + idString + ")");
            int i = stmt.executeUpdate();
            Map<Integer, List<Domino>> map1 = chain.getChains();
            StringBuilder query = new StringBuilder("");
            for (int n : map.keySet()) {
                List<Domino> list1 = map1.get(n);
                for (Domino domino : list1) {
                    query.append("select null, ").append(n).append(", '").append(chain.getMarket().getName()).append("', ").append(domino.getFirstNum()).append(", ").append(domino.getSecondNum()).append(" from dual union all ");
                }
            }
            stmt = connection.prepareStatement("INSERT INTO `domino`.`chain` " + query.substring(0, query.length() - 11));
            stmt.executeUpdate();
            connection.commit();
            if (i == 1) {
                return true;
            }
        } catch (MySQLIntegrityConstraintViolationException e2) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("exception while rollbacking changes");
            }
            throw new MySQLIntegrityConstraintViolationException();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("exception while rollbacking changes");
            }
            LOGGER.error("SQL exception while inserting market");
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean deleteMarket(String name) {
        getConnection();
        try {
            stmt = connection.prepareStatement("DELETE FROM market WHERE name = ?");
            stmt.setString(1, name);
            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while deleting market");
        } finally {
            close();
        }
        return false;
    }
}

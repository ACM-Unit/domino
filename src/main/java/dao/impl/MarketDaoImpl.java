package dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.MarketDao;
import entity.Chain;
import entity.Domino;
import entity.Market;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public class MarketDaoImpl implements MarketDao {
    private Logger LOGGER = Logger.getLogger(getClass());
    private DataSource dataSource;
    public MarketDaoImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getAllMarketNames() {
        List<String> names = null;
        Connection connection = null;PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement("SELECT distinct name FROM market");
            rs = stmt.executeQuery();
            names = new ArrayList<>();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while getting all markets");
        } finally {
            close(connection, stmt, rs, LOGGER);
        }
        return names;
    }

    @Override
    public Market getMarketByName(String name) {
        Market market = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
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
            close(connection, stmt, rs, LOGGER);
        }
        return market;
    }

    @Override
    public boolean insertMarket(Market market, String label) throws MySQLIntegrityConstraintViolationException, TimeoutException {
        Chain chain = new Chain();
        Map<Integer, List<Domino>> map = new HashMap<>();
        chain.setMarket(market);
        List<List<Domino>> list = new ArrayList<>();
        if("All".equals(label)) {
            list.addAll(market.getAllChains());
        }else{
            list.addAll(market.getLongestChains());
        }
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
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement("INSERT INTO `domino`.`market` SELECT '" + market.getName() + "', id FROM domino where id in (" + idString + ")");
            int i = stmt.executeUpdate();
            Map<Integer, List<Domino>> map1 = chain.getChains();
            StringBuilder query = new StringBuilder("INSERT INTO `domino`.`chain` ");
            List<String> listPlace = new ArrayList<>();
            for (int n : map.keySet()) {
                List<Domino> list1 = map1.get(n);
                for (Domino domino : list1) {
                    query.append("select null, ?, ?, ?, ? from dual union all ");
                    listPlace.add(String.valueOf(n));
                    listPlace.add(chain.getMarket().getName());
                    listPlace.add(String.valueOf(domino.getFirstNum()));
                    listPlace.add(String.valueOf(domino.getSecondNum()));
                }
            }
            stmt = connection.prepareStatement(query.substring(0, query.length() - 11));
            for (int j = 0; j < listPlace.size(); j++) {
                stmt.setString(j+1, listPlace.get(j));
            }
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
            close(connection, stmt, null, LOGGER);
        }
        return false;
    }

    @Override
    public boolean deleteMarket(String name) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement("DELETE FROM market WHERE name = ?");
            stmt.setString(1, name);
            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while deleting market");
        } finally {
            close(connection, stmt, null, LOGGER);
        }
        return false;
    }
}

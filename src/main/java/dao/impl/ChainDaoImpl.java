package dao.impl;

import dao.ChainDao;
import dao.MarketDao;
import dbConnection.DbConnection;
import entity.Chain;
import entity.Domino;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Viacheslav Koshchii on 11/6/2017.
 */
public class ChainDaoImpl extends DbConnection implements ChainDao {
    private Logger LOGGER = Logger.getLogger(getClass());

    @Override
    public Chain getChainByName(String name) {
        MarketDao dao = new MarketDaoImpl();
        getConnection();
        Chain chain = new Chain();
        Map<Integer, List<Domino>> map = new LinkedHashMap<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM chain where marketname = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            int currentvariant = 0;
            List<Domino> list = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                if (currentvariant != rs.getInt("variant") || rs.isLast()) {
                    if (rs.isLast()) {
                        list.add(domino);
                    }
                    map.put(currentvariant, list);
                    list = new ArrayList<>();
                    currentvariant = rs.getInt("variant");
                }
                list.add(domino);
            }
            chain.setMarket(dao.getMarketByName(name));
            chain.setChains(map);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return chain;
    }

    @Override
    public boolean insertChain(Chain chain) {
        getConnection();
        Map<Integer, List<Domino>> map = chain.getChains();
        try {
            String query = "";
            for (int n : map.keySet()) {
                List<Domino> list = map.get(n);
                for (Domino domino : list) {
                    query += "select null, " + n + ", '" + chain.getMarket().getName() + "', " + domino.getFirstNum() + ", " + domino.getSecondNum() + " from dual union all ";
                }
            }
            query = query.substring(0, query.length() - 11);
            LOGGER.info(query);
            stmt = connection.prepareStatement("INSERT INTO `domino`.`chain` " + query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return false;
        } finally {
            close();
        }
        return true;
    }


}

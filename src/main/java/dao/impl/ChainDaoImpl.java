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
            LOGGER.error("SQL exception while getting chain by name");
        } finally {
            close();
        }
        return chain;
    }


}

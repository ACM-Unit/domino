package dao.impl;

import dao.ChainDao;
import dao.MarketDao;
import entity.Chain;
import entity.Domino;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Viacheslav Koshchii on 11/6/2017.
 */
public class ChainDaoImpl implements ChainDao {
    private Logger LOGGER = Logger.getLogger(getClass());
    private MarketDao dao;
    private DataSource dataSource;
    public ChainDaoImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        dao = new MarketDaoImpl(dataSource);
    }

    @Override
    public Chain getChainByName(String name) {
        Chain chain = new Chain();
        Map<Integer, List<Domino>> map = new LinkedHashMap<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
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
            close(connection, stmt, rs, LOGGER);
        }
        return chain;
    }

}

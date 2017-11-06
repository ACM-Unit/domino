package dao.impl;

import dao.ChainDao;
import dao.MarketDao;
import dbConnection.ConnectionFactory;
import entity.Chain;
import entity.Domino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by viko0417 on 11/6/2017.
 */
public class ChainDaoImpl implements ChainDao {
    @Override
    public Chain getChainByName(String name) {
        MarketDao dao = new MarketDaoImpl();
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        Chain chain = new Chain();
        Map<Integer, List<Domino>> map = new LinkedHashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM chain where marketname = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            int currentvariant = 0;
            List<Domino> list = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                if(currentvariant!=rs.getInt("variant")|| rs.isLast()){
                    if(rs.isLast()){
                        list.add(domino);
                    }
                    map.put(currentvariant, list);
                    list = new ArrayList<>();
                    currentvariant=rs.getInt("variant");
                }
                list.add(domino);
            }
            chain.setMarket(dao.getMarketByName(name));
            chain.setChains(map);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chain;
    }

    @Override
    public boolean insertChain(Chain chain) {
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        Map<Integer, List<Domino>> map = chain.getChains();
        try {
            String query ="";
            for(int n:map.keySet()) {
                String idString = "";
                List<Domino> list = map.get(n);
                for(Domino domino:list){
                    query+="select null, "+n+", '"+ chain.getMarket().getName()+"', "+domino.getFirstNum()+", "+domino.getSecondNum()+" from dual union all ";
                }
            }
            query = query.substring(0, query.length() - 11);
            System.out.println(query);
            stmt = connection.prepareStatement("INSERT INTO `domino`.`chain` "+query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}

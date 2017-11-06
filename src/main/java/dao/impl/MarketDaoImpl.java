package dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.MarketDao;
import dbConnection.ConnectionFactory;
import entity.Market;
import entity.Domino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 05.11.2017.
 */
public class MarketDaoImpl implements MarketDao {

    @Override
    public List<String> getAllMarketNames() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        List<String> names = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT distinct name FROM market");
            rs = stmt.executeQuery();
            names = new ArrayList<>();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
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
        return names;
    }

    @Override
    public Market getMarketByName(String name) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        Market market = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino d, market m where d.id = m.domino  and m.name = ?");
            stmt.setString(1, name);
            rs=stmt.executeQuery();
            List<Domino> list = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId(rs.getInt("id"));
                list.add(domino);
            }
            market = new Market(list, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return market;
    }

    @Override
    public boolean insertMarket(Market market) throws MySQLIntegrityConstraintViolationException {
        String idString = "";
        for(int i=0; i<market.getMarket().size(); i++){
            if(i==market.getMarket().size()-1){
                idString +=market.getMarket().get(i).getId();
            }else{
                idString+=market.getMarket().get(i).getId()+",";
            }
        }
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO `domino`.`market` SELECT '"+market.getName()+"', id FROM domino where id in ("+idString+")");
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (MySQLIntegrityConstraintViolationException e2){
            throw new MySQLIntegrityConstraintViolationException();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteMarket(String[] names) {
        String idString = "";
        for(int i=0; i<names.length; i++){
            if(i==names.length-1){
                idString +="'"+names[i]+"'";
            }else{
                idString+="'"+names[i]+"',";
            }
        }
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM market WHERE name in (" + idString+")");
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

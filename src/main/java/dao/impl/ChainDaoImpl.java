package dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dbConnection.ConnectionFactory;
import entity.Chain;
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
public class ChainDaoImpl implements ChainDao {

    @Override
    public List<String> getAllChainNames() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        List<String> names = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT distinct name FROM chain c");
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
    public Chain getChainByIds(String name) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        Chain chain = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino d, chain c where d.id = c.domino  and c.name = ?");
            stmt.setString(1, name);
            List<Domino> list = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId(rs.getInt("id"));
                list.add(domino);
            }
            chain = new Chain(list, name);
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
        return chain;
    }

    @Override
    public boolean insertChain(Chain chain) throws MySQLIntegrityConstraintViolationException {
        String idString = "";
        for(int i=0; i<chain.getChain().size(); i++){
            if(i==chain.getChain().size()-1){
                idString +=chain.getChain().get(i).getId();
            }else{
                idString+=chain.getChain().get(i).getId()+",";
            }
        }
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO CHAIN (name, domino)SELECT '"+chain.getName()+"', id FROM domino where id in ("+idString+")");
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
    public boolean deleteChain(String[] names) {
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
            stmt = connection.prepareStatement("DELETE FROM chain WHERE name in (" + idString+")");
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

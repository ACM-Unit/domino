package dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dbConnection.ConnectionFactory;
import entity.Domino;
import entity.Market;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by viko0417 on 11/6/2017.
 */
public class ChainDaoImpl implements ChainDao {
    @Override
    public Set<List<Domino>> getChainByName(String name) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        Set<List<Domino>> set = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino d, chain c where d.id = c.domino  and c.marketname = ?");
            stmt.setString(1, name);
            int currentvariant = 1;
            List<Domino> list = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId(rs.getInt("id"));
                list.add(domino);
                if(currentvariant!=rs.getInt("variant")){
                    set.add(list);
                    list = new ArrayList<>();
                    currentvariant=rs.getInt("variant");
                }
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
        return set;
    }

    @Override
    public boolean insertChain(Set<List<Domino>> set, String name) {
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        List<List<Domino>> list = new ArrayList();
        list.addAll(set);
        System.out.println("size: "+list.size());
        try {
            for(int n = 0; n < list.size(); n++) {
                String idString = "";
                for(int i=0; i<list.get(n).size(); i++){
                    if(i==list.get(n).size()-1){
                        idString +=list.get(n).get(i).getId();
                        System.out.println(idString);
                    }else{
                        idString+=list.get(n).get(i).getId()+",";
                        System.out.println(idString);
                    }
                }
                stmt = connection.prepareStatement("INSERT INTO `domino`.`chain` SELECT "+(n+1)+", '" + name + "', id FROM domino where id in (" + idString + ");");
                int i = stmt.executeUpdate();
                System.out.println("==========="+i);
            }
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

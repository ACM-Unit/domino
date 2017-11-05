package dao.impl;

import dao.ChainDao;
import dao.DominoDao;
import dbConnection.ConnectionFactory;
import entity.Chain;
import entity.Domino;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 05.11.2017.
 */
public class ChainDaoImpl implements ChainDao {


    @Override
    public Set<Domino> getAllChain() {
        return null;
    }

    @Override
    public List<Domino> getChainByIds(String name) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        List<Domino> chain = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino d, chain c where d.id = c.domino  and c.name = ?");
            stmt.setString(1, name);
            DominoDao dominoDao = new DominoDaoImpl();
            chain = new ArrayList<>();
            while (rs.next()) {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId(rs.getInt("id"));
                chain.add(domino);
            }
            Chain domino = new Chain(chain, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chain;
    }

    @Override
    public boolean insertCircuit(Chain chain) {
        return false;
    }

    @Override
    public boolean deleteDomino(Chain chain) {
        return false;
    }
}

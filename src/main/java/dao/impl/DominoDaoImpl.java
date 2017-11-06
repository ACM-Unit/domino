package dao.impl;

import dao.DominoDao;
import dbConnection.ConnectionFactory;
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
public class DominoDaoImpl implements DominoDao {


    @Override
    public List<Domino> getAllDominos() {
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino");
            rs = stmt.executeQuery();
            List dominoset = new ArrayList();
            while(rs.next())
            {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId( rs.getInt("id") );
                dominoset.add(domino);
            }
            return dominoset;
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
        return null;
    }

    @Override
    public List<Domino> getDominosByIds(String idString) {
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino where id in ("+idString+")");
            rs = stmt.executeQuery();
            List list = new ArrayList();
            while(rs.next())
            {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId( rs.getInt("id") );
                list.add(domino);
            }
            return list;
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
        return null;
    }

    @Override
    public boolean insertDomino(Domino domino) {
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO domino VALUES (NULL, ?, ?)");
            ps.setInt(1, domino.getFirstNum());
            ps.setInt(2, domino.getSecondNum());
            int i = ps.executeUpdate();
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

    @Override
    public boolean updateDomino(Domino domino) {
        ConnectionFactory connector = new ConnectionFactory() ;
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE domino SET firstNum=?, secondNum=? WHERE id=?");
            stmt.setInt(1, domino.getFirstNum());
            stmt.setInt(2, domino.getSecondNum());
            stmt.setInt(3, domino.getId());
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

    @Override
    public boolean deleteDomino(Domino domino) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM domino WHERE id=" + domino.getId());
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

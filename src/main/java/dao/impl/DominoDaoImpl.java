package dao.impl;

import dao.DominoDao;
import dbConnection.DbConnection;
import entity.Domino;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public class DominoDaoImpl  extends DbConnection implements DominoDao {
    private Logger LOGGER = Logger.getLogger(getClass());

    @Override
    public List<Domino> getAllDominoes() {
        getConnection();
        try {
            stmt = connection.prepareStatement("SELECT * FROM domino");
            rs = stmt.executeQuery();
            List dominoeset = new ArrayList();
            while(rs.next())
            {
                Domino domino = new Domino(rs.getInt("firstNum"), rs.getInt("secondNum"));
                domino.setId( rs.getInt("id") );
                dominoeset.add(domino);
            }
            return dominoeset;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    @Override
    public List<Domino> getDominoesByIds(String idString) {
        getConnection();
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
            LOGGER.error("SQL exception while getting domino by id");
        } finally {
            close();
        }
        return null;
    }

    @Override
    public boolean insertDomino(Domino domino) {
        getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO domino VALUES (NULL, ?, ?)");
            ps.setInt(1, domino.getFirstNum());
            ps.setInt(2, domino.getSecondNum());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while inserting domino");
        }finally {
            close();
        }
        return false;
    }

    @Override
    public boolean updateDomino(Domino domino) {
        getConnection();
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
            LOGGER.error("SQL exception while updating domino");
        }finally {
            close();
        }
        return false;
    }

    @Override
    public boolean deleteDomino(Domino domino) {
        getConnection();
        try {
            stmt = connection.prepareStatement("DELETE FROM domino WHERE id=" + domino.getId());
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception while deleting domino");
        }finally {
           close();
        }
        return false;
    }

}

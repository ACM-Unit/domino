package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entity.Domino;
import entity.Market;

import java.util.List;
import java.util.Set;

/**
 * Created by viko0417 on 11/6/2017.
 */
public interface ChainDao {
    Set<List<Domino>> getChainByName(String name);
    boolean insertChain(Set<List<Domino>> set, String name) throws MySQLIntegrityConstraintViolationException;
}

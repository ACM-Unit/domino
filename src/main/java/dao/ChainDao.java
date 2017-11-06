package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entity.Chain;

/**
 * Created by viko0417 on 11/6/2017.
 */
public interface ChainDao {
    Chain getChainByName(String name);
    boolean insertChain(Chain chain) throws MySQLIntegrityConstraintViolationException;
}

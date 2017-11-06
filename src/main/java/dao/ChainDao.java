package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entity.Chain;

import java.util.List;

/**
 * Created by Admin on 05.11.2017.
 */
public interface ChainDao {
    List<String> getAllChainNames();
    Chain getChainByIds(String name);
    boolean insertChain(Chain chain) throws MySQLIntegrityConstraintViolationException;
    boolean deleteChain(String[] names);
}

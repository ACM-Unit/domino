package services.impl;

import dao.ChainDao;
import dao.impl.ChainDaoImpl;
import entity.Chain;
import services.ChainService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Viacheslav Koshchii on 06.11.2017.
 */
public class ChainServiceImpl implements ChainService {

    public ChainServiceImpl(DataSource dataSource) throws SQLException {
        chainDao = new ChainDaoImpl(dataSource);
    }

    private ChainDao chainDao;
    public Chain getChainsByName(String name){
        return chainDao.getChainByName(name);
    }
}

package services;

import dao.ChainDao;
import dao.impl.ChainDaoImpl;
import entity.Chain;

/**
 * Created by Viacheslav Koshchii on 06.11.2017.
 */
public class ChainService {
    private ChainDao chainDao = new ChainDaoImpl();
    public Chain getChainsByName(String name){
        return chainDao.getChainByName(name);
    }
}

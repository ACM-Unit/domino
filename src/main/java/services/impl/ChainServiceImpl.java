package services.impl;

import dao.ChainDao;
import dao.impl.ChainDaoImpl;
import entity.Chain;
import services.ChainService;

/**
 * Created by Viacheslav Koshchii on 06.11.2017.
 */
public class ChainServiceImpl implements ChainService {
    private ChainDao chainDao = new ChainDaoImpl();
    public Chain getChainsByName(String name){
        return chainDao.getChainByName(name);
    }
}

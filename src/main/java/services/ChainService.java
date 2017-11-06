package services;

import dao.ChainDao;
import dao.impl.ChainDaoImpl;

import java.util.List;

/**
 * Created by Admin on 06.11.2017.
 */
public class ChainService {
    private ChainDao dao = new ChainDaoImpl();
    public List<String> getAllNames() {
        return dao.getAllChainNames();
    }
}

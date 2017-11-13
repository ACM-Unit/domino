package dao;

import entity.Chain;

/**
 * Class for work with chains of dominoes from Database
 * Created by Viacheslav Koshchii on 11/6/2017.
 */
public interface ChainDao {
    /**
     * get all chains of dominoes which have given name from Data Base
     * @param name
     * @return
     */
    Chain getChainByName(String name);
}

package services;

import entity.Chain;

/**
 * Created by Admin on 07.11.2017.
 */
public interface ChainService {
    Chain getChainsByName(String name);
}

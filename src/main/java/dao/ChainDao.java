package dao;

import entity.Chain;
import entity.Domino;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 05.11.2017.
 */
public interface ChainDao {
    Set<Domino> getAllChain();
    List<Domino> getChainByIds(String name);
    boolean insertCircuit(Chain chain);
    boolean deleteDomino(Chain chain);
}

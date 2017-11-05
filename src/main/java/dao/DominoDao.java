package dao;

import entity.Domino;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 05.11.2017.
 */
public interface DominoDao {
    Set<Domino> getAllDominos();
    List<Domino> getDominosByIds(Integer ids[]);
    boolean insertDomino(Domino domino);
    boolean updateDomino(Domino domino);
    boolean deleteDomino(Domino domino);
}

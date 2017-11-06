package dao;

import entity.Domino;

import java.util.List;

/**
 * Created by Admin on 05.11.2017.
 */
public interface DominoDao {
    List<Domino> getAllDominos();
    List<Domino> getDominosByIds(String idString);
    boolean insertDomino(Domino domino);
    boolean updateDomino(Domino domino);
    boolean deleteDomino(Domino domino);
}

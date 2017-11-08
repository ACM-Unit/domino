package services;

import entity.Domino;

import java.util.List;

/**
 * Created by Admin on 07.11.2017.
 */
public interface DominoService {
    List<Domino> getAll();
    List<Domino> getRandom(int num);
    List<Domino> getByIds(String idString);
}

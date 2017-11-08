package dao;

import entity.Domino;

import java.util.List;

/**
 * Class for work with Domino from Database
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public interface DominoDao {
    /**
     * get all dominoes from database
     * @return
     */
    List<Domino> getAllDominoes();

    /**
     * method get all dominoes by ids from database
     * @param idString
     * @return
     */
    List<Domino> getDominoesByIds(String idString);

    /**
     * method for save domino into database
     * @param domino
     * @return
     */
    boolean insertDomino(Domino domino);

    /**
     * method for update domino into database
     * @param domino
     * @return
     */
    boolean updateDomino(Domino domino);

    /**
     * method for delete domino into database
     * @param domino
     * @return
     */
    boolean deleteDomino(Domino domino);
}

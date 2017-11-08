package services.impl;

import dao.DominoDao;
import dao.impl.DominoDaoImpl;
import entity.Domino;
import services.DominoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public class DominoServiceImpl implements DominoService{
    private DominoDao dao = new DominoDaoImpl();

    public List<Domino> getAll() {
        return dao.getAllDominoes();
    }
    public List<Domino> getRandom(int num){
       List<Domino> list = dao.getAllDominoes();
       List<Domino> random = new ArrayList<>();
       for(int i = 0; i<num; i++){
           Random r = new Random();
           int id = r.nextInt((list.size()));
           random.add(list.get(id));
           list.remove(id);
       }
       return random;
    }
    public List<Domino> getByIds(String idString){
        return dao.getDominoesByIds(idString);
    }
}

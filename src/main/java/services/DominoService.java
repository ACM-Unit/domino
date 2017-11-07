package services;

import dao.DominoDao;
import dao.impl.DominoDaoImpl;
import entity.Domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Viacheslav Koshchii on 05.11.2017.
 */
public class DominoService {
    private DominoDao dao = new DominoDaoImpl();

    public List<Domino> getAll() {
        return dao.getAllDominos();
    }
    public List<Domino> getRandom(int num){
       List<Domino> list = dao.getAllDominos();
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
        return dao.getDominosByIds(idString);
    }
}

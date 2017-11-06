import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.DominoDao;
import dao.impl.ChainDaoImpl;
import dao.impl.DominoDaoImpl;
import entity.Chain;
import entity.Domino;
import org.junit.Test;
import services.DominoService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 03.11.2017.
 */
public class testDomino {
    private DominoService service = new DominoService();

    @Test
    public void test() {
        List<Domino> list = service.getByIds("5,1,9,8,4");
        Chain chain = null;
        try {
            chain = new Chain(list, "new");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Set<String> set = new HashSet<String>();
        for (List<Domino> chains : chain.getLongestChains()) {
            String s = "";
            for (Domino domino : chains) {
                s += domino.getFirstNum() + ":" + domino.getSecondNum() + " ; ";
            }
            set.add(s);
            System.out.println(s);
        }
        System.out.println(set.size());
    }

    @Test
    public void testDomino() {
        DominoDao dao = new DominoDaoImpl();
        List<Domino> listDomino = dao.getDominosByIds("5, 8, 11, 15, 25");
        for (Domino domino : listDomino) {
            System.out.println(domino.toString());
        }
        try {
            Chain chain = new Chain(listDomino, "superChain");
            ChainDao dao1 = new ChainDaoImpl();
            dao1.insertChain(chain);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("name not unique");
        }

    }

    @Test
    public void testDeleteChain() {
        ChainDao dao1 = new ChainDaoImpl();
        dao1.deleteChain(new String[]{"newChain"});
    }
    @Test
    public void testRandom(){
        DominoService service = new DominoService();
        for(Domino domino: service.getRandom(28)){
            System.out.println(domino);
        }
    }
}

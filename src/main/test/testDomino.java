import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.MarketDao;
import dao.DominoDao;
import dao.impl.ChainDaoImpl;
import dao.impl.MarketDaoImpl;
import dao.impl.DominoDaoImpl;
import entity.Market;
import entity.Domino;
import org.junit.Test;
import services.DominoService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Viacheslav Koshchii on 03.11.2017.
 */
public class testDomino {
    private DominoService service = new DominoService();

    @Test
    public void test() {
        List<Domino> list = service.getByIds("5,1,9,8,4");
        Market market = null;
        try {
            market = new Market(list, "new");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Set<String> set = new HashSet<String>();
        for (List<Domino> markets : market.getLongestChains()) {
            String s = "";
            for (Domino domino : markets) {
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
        Market market = null;
        try {
            market = new Market(listDomino, "new Market");
            MarketDao dao1 = new MarketDaoImpl();
            dao1.insertMarket(market);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("name not unique");
        }

    }

    @Test
    public void testDeleteMarket() {
        MarketDao dao1 = new MarketDaoImpl();
        dao1.deleteMarket("newMarket");
    }
    @Test
    public void testRandom(){
        DominoService service = new DominoService();
        for(Domino domino: service.getRandom(28)){
            System.out.println(domino);
        }
    }
}

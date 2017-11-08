import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.ChainDao;
import dao.DominoDao;
import dao.MarketDao;
import dao.impl.ChainDaoImpl;
import dao.impl.DominoDaoImpl;
import dao.impl.MarketDaoImpl;
import entity.Domino;
import entity.Market;
import org.junit.Test;
import services.impl.DominoServiceImpl;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Viacheslav Koshchii on 03.11.2017.
 */
public class testDomino {
    private DominoServiceImpl service = new DominoServiceImpl();

    @Test
    public void testAlgorithm() {
        List<Domino> list = service.getByIds("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18");
        Market market = new Market(list, "new");
        try {
            assert(market.AllChainsToString().equals("4:0, 0:3;\n" +
                    "1:4, 4:0, 0:3;\n" +
                    "3:0, 0:4, 4:1, 1:2;\n" +
                    "2:1, 1:4, 4:0, 0:3;\n" +
                    "0:4, 4:1, 1:2;\n" +
                    "4:1, 1:2;"));
        } catch (TimeoutException e) {
            System.out.println("terminated");
            e.printStackTrace();
        }
    }

    @Test
    public void testMarket() {
        DominoDao dao = new DominoDaoImpl();
        List<Domino> listDomino = dao.getDominoesByIds("5, 8, 11, 15, 25");
        for (Domino domino : listDomino) {
            System.out.println(domino.toString());
        }
        Market market = null;
        MarketDao dao1 = new MarketDaoImpl();
        ChainDao dao2 = new ChainDaoImpl();
        try {
            market = new Market(listDomino, "new Market");
            dao1.insertMarket(market);
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("name not unique");
        }
        assert (dao1.getMarketByName("new Market").getMarket().size()!=0);
    }

    @Test
    public void testDeleteMarket() {
        MarketDao dao1 = new MarketDaoImpl();
        ChainDao dao2 = new ChainDaoImpl();
        System.out.println(dao1.getMarketByName("MyAllChains").getMarket().size()==0 && dao2.getChainByName("MyAllChains").getChains().size()==0);
        dao1.deleteMarket("MyAllChains");
        assert (dao1.getMarketByName("MyAllChains").getMarket().size()==0 && dao2.getChainByName("MyAllChains").getChains().size()==0);
    }
    @Test
    public void testRandom(){
        DominoServiceImpl service = new DominoServiceImpl();
        for(Domino domino: service.getRandom(28)){
            System.out.println(domino);
        }
    }
}

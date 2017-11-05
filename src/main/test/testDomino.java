import dao.DominoDao;
import dao.impl.DominoDaoImpl;
import entity.Chain;
import entity.Domino;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 03.11.2017.
 */
public class testDomino {


    @Test
    public void test(){
        List<Domino> list = new ArrayList<Domino>();
        list.add(new Domino(1, 2));
        list.add(new Domino(2, 4));
        list.add(new Domino(1, 4));
        list.add(new Domino(3, 4));
        Chain chain = null;
        try {
            chain = new Chain(list, "new");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Set<String> set = new HashSet<String>();
        for(List<Domino> chains: chain.getAllChains()){
            String s = "";
            for(Domino domino: chains){
                s+=domino.getFirstNum()+":"+domino.getSecondNum()+" ; ";
            }
            set.add(s);
            System.out.println(s);
        }
        System.out.println(set.size());
    }
    @Test
    public void testDomino(){
        DominoDao dao = new DominoDaoImpl();
        for(Domino domino: dao.getDominosByIds(new Integer[]{1,2,4,12,15})){
            System.out.println(domino.toString());
        }
    }
}

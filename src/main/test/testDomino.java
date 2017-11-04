import entity.Bank;
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
        Bank bank = null;
        try {
            bank = new Bank(list);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Set<String> set = new HashSet<String>();
        for(List<Domino> banks: bank.getAllBanks()){
            String s = "";
            for(Domino domino: banks){
                s+=domino.getFirstNum()+":"+domino.getSecondNum()+" ; ";
            }
            set.add(s);
            System.out.println(s);
        }
        System.out.println(set.size());
    }
}

package entity;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Viacheslav Koshchii on 03.11.2017.
 */
public class Market {
    private String name;
    private List<Domino> market;
    private Set<List<Domino>> allChains = new HashSet<List<Domino>>();
    private Logger LOGGER = Logger.getLogger(getClass());
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Domino> getMarket() {
        return market;
    }

    public void setMarket(List<Domino> market) {
        this.market = market;
    }

    public Set<List<Domino>> getAllChains() {
        return allChains;
    }

    public void setAllChains(Set<List<Domino>> allChains) {
        this.allChains = allChains;
    }

    public Market(List<Domino> market, String name) throws CloneNotSupportedException {
        this.market = market;
        this.name = name;
        for(int i=0; i<market.size(); i++) {
            Domino domino = market.get(i).clone();
            List<Domino> next = new ArrayList<Domino>(market);
            next.remove(i);
            List<Domino> currentList = new ArrayList<Domino>();
            currentList.add(market.get(i));
            getAllChains(next, currentList);
            if(domino.getFirstNum()!=domino.getSecondNum()) {
                domino.rotate();
                List<Domino> currentList1 = new ArrayList<Domino>();
                currentList1.add(domino);
                getAllChains(next, currentList1);
            }
        }
    }

    private void getAllChains(List<Domino>list, List<Domino> currentList) throws CloneNotSupportedException {
        boolean stop = false;
        Domino current = currentList.get(currentList.size()-1);
        Iterator<Domino> iter = list.iterator();
        while(iter.hasNext()) {
            Domino dominoOrigin = iter.next();
            Domino domino = dominoOrigin.clone();
            if(current.join(domino)){
                List<Domino> next = new ArrayList<Domino>(list);
                next.remove(dominoOrigin);
                List<Domino> currentListNext = new ArrayList<Domino>(currentList);
                currentListNext.add(domino);
                getAllChains(next, currentListNext);
                stop = true;
            }
        }
        if(currentList.size()>1 && !stop){
            allChains.add(currentList);
        }
    }
    public Set<List<Domino>> getLongestChains(){
        int max = 0;
        for (List<Domino> list: allChains) {
            if(list.size()>max){
                max = list.size();
            }
        }
        Set<List<Domino>> set = new HashSet<>();
        for (List<Domino> list: allChains) {
            if(list.size()==max){
                set.add(list);
            }
        }
        return set;
    }
}

package entity;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;

/**
 * Class for work with sets of dominoes
 * Created by Viacheslav Koshchii on 03.11.2017.
 */
public class Market implements Callable<String> {
    public static final int TIMEOUT=3;
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

    public Set<List<Domino>> getAllChains() throws TimeoutException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(this);
        try {
            future.get(TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("Error while execute in thread");
        } catch (ExecutionException e) {
            LOGGER.error("Error while execute");
        }finally {
            executor.shutdown();
        }

        return allChains;
    }

    public void setAllChains(Set<List<Domino>> allChains) {
        this.allChains = allChains;
    }

    /**
     * Constructor which create set of Dominoes and find all possible chains builds from this set
     * @param market
     * @param name
     * @throws CloneNotSupportedException
     */
    public Market(List<Domino> market, String name) {
        this.market = market;
        this.name = name;
    }
    @Override
    public String call() throws Exception {
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
        return "Ready!";
    }
    /**
     * Recursive method for find all possible chains builds from given set
     * @param list
     * @param currentList
     * @throws CloneNotSupportedException
     */
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

    /**
     * method which find the longest from all possible chains
     * @return
     */
    public Set<List<Domino>> getLongestChains() throws TimeoutException {
        int max = 0;
        for (List<Domino> list: getAllChains()) {
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
    public String AllChainsToString() throws TimeoutException {
        Set<List<Domino>> list = getAllChains();
        String chainStr = "";
        for(List<Domino> chain: list){
            for(Domino domino: chain){
                chainStr += domino.getFirstNum()+":"+domino.getSecondNum()+", ";
            }
            chainStr = chainStr.substring(0, chainStr.length()-2)+";\n";
        }
        return chainStr.substring(0, chainStr.length()-1);
    }

}

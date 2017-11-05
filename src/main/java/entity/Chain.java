package entity;

import java.util.*;

/**
 * Created by Admin on 03.11.2017.
 */
public class Chain {
    private String name;
    private List<Domino> chain;
    private Set<List<Domino>> allChains = new HashSet<List<Domino>>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Domino> getChain() {
        return chain;
    }

    public void setChain(List<Domino> chain) {
        this.chain = chain;
    }

    public Set<List<Domino>> getAllChains() {
        return allChains;
    }

    public void setAllChains(Set<List<Domino>> allChains) {
        this.allChains = allChains;
    }

    public Chain(List<Domino> chain, String name) throws CloneNotSupportedException {
        this.chain = chain;
        this.name = name;
        for(Domino domino: chain) {
            Domino domino1 = domino.clone();
            List<Domino> next = new ArrayList<Domino>(chain);
            next.remove(domino);
            List<Domino> currentList = new ArrayList<Domino>();
            currentList.add(domino);
            getAllChains(next, currentList);
            List<Domino> next1 = new ArrayList<Domino>(chain);
            next1.remove(domino1);
            domino1.rotate();
            List<Domino> currentList1 = new ArrayList<Domino>();
            currentList1.add(domino1);
            getAllChains(next1, currentList1);
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
}

package entity;

import java.util.*;

/**
 * Created by Admin on 03.11.2017.
 */
public class Bank {
    private int id;
    private String name;
    private List<Domino> bank;
    private List<Domino> longestBank;
    private Set<List<Domino>> allBanks = new HashSet<List<Domino>>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Domino> getBank() {
        return bank;
    }

    public void setBank(List<Domino> bank) {
        this.bank = bank;
    }

    public List<Domino> getLongestBank() {
        return longestBank;
    }

    public void setLongestBank(List<Domino> longestBank) {
        this.longestBank = longestBank;
    }

    public Set<List<Domino>> getAllBanks() {
        return allBanks;
    }

    public void setAllBanks(Set<List<Domino>> allBanks) {
        this.allBanks = allBanks;
    }

    public Bank(List<Domino> bank) throws CloneNotSupportedException {
        this.bank = bank;
        for(Domino domino: bank) {
            Domino domino1 = domino.clone();
            List<Domino> next = new ArrayList<Domino>(bank);
            next.remove(domino);
            List<Domino> currentList = new ArrayList<Domino>();
            currentList.add(domino);
            getAllBanks(next, currentList);
            List<Domino> next1 = new ArrayList<Domino>(bank);
            next1.remove(domino1);
            domino1.rotate();
            List<Domino> currentList1 = new ArrayList<Domino>();
            currentList1.add(domino1);
            getAllBanks(next1, currentList1);
        }
    }

    private void getAllBanks(List<Domino>list, List<Domino> currentList) throws CloneNotSupportedException {
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
                getAllBanks(next, currentListNext);
                stop = true;
            }
        }
        if(currentList.size()>1 && !stop){
            allBanks.add(currentList);
        }
    }
}

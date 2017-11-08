package services;

import entity.Market;

import java.util.List;

/**
 * Created by Admin on 07.11.2017.
 */
public interface MarketService {
    List<String> getAllNames();
    Market getMarketByName(String name);
    boolean saveMarketAndAllChains(Market market);
    boolean saveMarketAndLongestChains(Market market);
    void delete(String name);
}

package services;

import entity.Market;

import java.util.List;

/**
 * Created by Admin on 07.11.2017.
 */
public interface MarketService {
    List<String> getAllNames();
    Market getMarketByName(String name);
    boolean saveMarketAndChains(Market market, String label);
    void delete(String name);
}

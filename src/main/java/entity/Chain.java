package entity;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 06.11.2017.
 */
public class Chain {
    private Market market;
    private Map<Integer, List<Domino>> chains;

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Map<Integer, List<Domino>> getChains() {
        return chains;
    }

    public void setChains(Map<Integer, List<Domino>> chains) {
        this.chains = chains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chain)) return false;

        Chain chain = (Chain) o;

        if (getMarket() != null ? !getMarket().equals(chain.getMarket()) : chain.getMarket() != null) return false;
        return getChains() != null ? getChains().equals(chain.getChains()) : chain.getChains() == null;
    }

    @Override
    public int hashCode() {
        int result = getMarket() != null ? getMarket().hashCode() : 0;
        result = 31 * result + (getChains() != null ? getChains().hashCode() : 0);
        return result;
    }
}

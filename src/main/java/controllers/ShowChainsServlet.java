package controllers;

import entity.Chain;
import entity.Domino;
import entity.Market;
import listeners.Config;
import org.apache.log4j.Logger;
import services.ChainService;
import services.DominoService;
import services.MarketService;
import services.impl.ChainServiceImpl;
import services.impl.DominoServiceImpl;
import services.impl.MarketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet that handles HTTP requests that come from the url: /get-market
 * Created by Viacheslav Koshchii on 07.11.2017.
 */
public class ShowChainsServlet extends MainServlet {

    private Logger LOGGER = Logger.getLogger(getClass());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int begin = Integer.parseInt(request.getParameter("begin"));
        int end = Integer.parseInt(request.getParameter("end"));
        if(begin==1){
            begin = -50;
        }
        begin+=50;
        end+=50;
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("names", marketService.getAllNames());
        String name = request.getParameter("marketname");
        String idString = request.getParameter("ids");
        String title = request.getParameter("title");
        boolean timeout = false;
        boolean saved = false;
        List<List<Domino>> list = new ArrayList<>();
        if(!name.equals("-")) {
            Chain markets = chainService.getChainsByName(name);
            Market m = marketService.getMarketByName(name);
            list.addAll(markets.getChains().values());
            idString="";
            saved = true;
            for(int i=0; i<m.getMarket().size(); i++){
                if(i==m.getMarket().size()-1){
                    idString +=m.getMarket().get(i).getId();
                }else{
                    idString+=m.getMarket().get(i).getId()+",";
                }
            }
        }else{
            List<Domino> dominoes = dominoService.getByIds(idString);
            request.setAttribute("showDomino", dominoes);
            Market market = null;
            market = new Market(dominoes, "");
            if(title.equals("All")) {
                try {
                    list.addAll(market.getAllChains());
                } catch (Exception e) {
                    LOGGER.info("user requested too much data");
                    timeout=true;
                }
            }else if(title.equals("Longest")){
                try {
                    list.addAll(market.getLongestChains());
                } catch (Exception e) {
                    timeout=true;
                    LOGGER.info("user requested too much data");
                }
            }
        }
        request.setAttribute("saved", saved);
        request.setAttribute("timeout", timeout);
        request.setAttribute("title", title);
        request.setAttribute("marketSize", list.size());
        request.setAttribute("ids", idString);
        request.setAttribute("markets", list);
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("names", marketService.getAllNames());
        request.setAttribute("title", "All");
        request.setAttribute("marketSize", 0);
        request.setAttribute("ids", "0");
        request.setAttribute("markets", new ArrayList<>());
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }
}

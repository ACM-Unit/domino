package controllers;

import entity.Chain;
import entity.Market;
import entity.Domino;
import services.ChainService;
import services.MarketService;
import services.DominoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Viacheslav Koshchii on 06.11.2017.
 */
public class ShowChainsServlet extends HttpServlet {
    private DominoService service = new DominoService();
    private MarketService marketService = new MarketService();
    private ChainService chainService = new ChainService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("names", marketService.getAllNames());
        String name = request.getParameter("marketname");
        String idString = request.getParameter("ids");
        String title = request.getParameter("title");
        List<List<Domino>> list = new ArrayList<>();
        if(!name.equals("-")) {
            Chain markets = chainService.getChainsByName(name);
            list.addAll(markets.getChains().values());
        }else{
            List<Domino> dominoes = service.getByIds(idString);
            request.setAttribute("showDomino", dominoes);
            Market market = null;
            try {
                market = new Market(dominoes, "");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if(title.equals("All")) {
                list.addAll(market.getAllChains());
            }else if(title.equals("Longest")){
                list.addAll(market.getLongestChains());
            }
        }
        request.setAttribute("title", title);
        request.setAttribute("marketSize", list.size());
        request.setAttribute("ids", idString);
        request.setAttribute("markets", list);
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("names", marketService.getAllNames());
        request.setAttribute("title", "All");
        request.setAttribute("marketSize", 0);
        request.setAttribute("ids", "0");
        request.setAttribute("markets", new ArrayList<>());
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }
}

package controllers;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 06.11.2017.
 */
public class ShowMarketServlet extends HttpServlet {
    private DominoService service = new DominoService();
    private MarketService marketService = new MarketService();
    private ChainService chainService = new ChainService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("names", marketService.getAllNames());
        String idString = request.getParameter("ids");
        System.out.println("ids: "+ idString);
        List<Domino> dominoes = service.getByIds(idString);
        request.setAttribute("showDomino", dominoes);
        Market market = null;
        try {
            market = new Market(dominoes, "");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Set<String> set = new HashSet<String>();
        for (List<Domino> markets : market.getAllChains()) {
            String s = "";
            for (Domino domino : markets) {
                s += domino.getFirstNum() + ":" + domino.getSecondNum() + " ; ";
            }
            set.add(s);
            System.out.println(s);
        }
        System.out.println(set.size());
        request.setAttribute("ids", idString);
        request.setAttribute("markets", market.getAllChains());
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("names", marketService.getAllNames());
        String idString = request.getParameter("ids");
        System.out.println("ids: "+ idString);
        List<Domino> dominoes = service.getByIds(idString);
        request.setAttribute("showDomino", dominoes);
        Market market = null;
        try {
            market = new Market(dominoes, "");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        request.setAttribute("ids", idString);
        request.setAttribute("markets", market.getLongestMarkets());
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }


}

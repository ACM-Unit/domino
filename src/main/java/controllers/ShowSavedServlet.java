package controllers;

import entity.Chain;
import services.ChainService;
import services.MarketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 06.11.2017.
 */
public class ShowSavedServlet extends HttpServlet {
    private MarketService marketService = new MarketService();
    private ChainService chainService = new ChainService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idString = request.getParameter("ids");
        String name = request.getParameter("marketname");
        System.out.println(idString);
        System.out.println(name);
        request.setAttribute("names", marketService.getAllNames());
        Chain markets = chainService.getChainsByName(name);
        request.setAttribute("ids", idString);
        request.setAttribute("markets", markets.getChains().values());
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }

}

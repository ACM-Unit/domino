package controllers;

import services.MarketService;
import services.DominoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 06.11.2017.
 */
public class MarketNameServlet extends HttpServlet {
    private DominoService service = new DominoService();
    private MarketService marketService = new MarketService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getParameter("marketname"));
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }

}

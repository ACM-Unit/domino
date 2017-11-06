package controllers;

import services.MarketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 07.11.2017.
 */
public class DeleteServlet extends HttpServlet {
    private MarketService marketService = new MarketService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("marketname");
        marketService.delete(new String[]{name});
        request.getRequestDispatcher("/pages/showMarket.jsp").forward(request,response);
    }
}

package controllers;

import org.apache.log4j.Logger;
import services.MarketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Viacheslav Koshchii on 07.11.2017.
 */
public class DeleteServlet extends HttpServlet {
    private MarketService marketService = new MarketService();
    private Logger LOGGER = Logger.getLogger(getClass());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("market");
        marketService.delete(name);
        LOGGER.info("deleted chain with name - "+ name);
    }
}

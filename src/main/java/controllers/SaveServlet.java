package controllers;

import entity.Market;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles HTTP requests that come from the url: /save-chains
 * Created by Viacheslav Koshchii on 07.11.2017.
 */
public class SaveServlet  extends MainServlet {
    private Logger LOGGER = Logger.getLogger(getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
            String title = request.getParameter("title");
            Market market = new Market(dominoService.getByIds(request.getParameter("ids")), request.getParameter("chainName"));
             context.getJdbcObj().printDbStatus();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            boolean status = false;
            if(title.equals("All")){
                status = marketService.saveMarketAndAllChains(market);
                context.getJdbcObj().printDbStatus();
            }else if(title.equals("Longest")) {
                status = marketService.saveMarketAndLongestChains(market);
                context.getJdbcObj().printDbStatus();
            }
            if(status){
                response.getWriter().write(request.getParameter("chainName"));
                LOGGER.info("user saved new set of chain with name - " + request.getParameter("chainName"));
            }else{
                response.getWriter().write("Error, name already exists");
                LOGGER.debug("user tried to inpute name which already exists");
            }

    }

}

package controllers;

import entity.Market;
import org.apache.log4j.Logger;
import services.DominoService;
import services.MarketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Viacheslav Koshchii on 11/6/2017.
 */
public class SaveServlet  extends HttpServlet {
    private DominoService service = new DominoService();
    private MarketService marketService = new MarketService();
    private Logger LOGGER = Logger.getLogger(getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            String title = request.getParameter("title");
            Market market = new Market(service.getByIds(request.getParameter("ids")), request.getParameter("chainName"));
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            boolean status = false;
            if(title.equals("All")){
                status = marketService.saveMarketAndAllChains(market);
            }else if(title.equals("Longest")) {
                status = marketService.saveMarketAndLongestChains(market);
            }
            if(status){
                response.getWriter().write(request.getParameter("chainName"));
                LOGGER.info("user saved new set of chain with name - " + request.getParameter("chainName"));
            }else{
                response.getWriter().write("Error, name already exists");
                LOGGER.debug("user tried to inpute name which already exists");
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

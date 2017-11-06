package controllers;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import entity.Market;
import services.DominoService;
import services.MarketService;
import sun.security.provider.certpath.OCSPResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by viko0417 on 11/6/2017.
 */
public class SaveServlet  extends HttpServlet {
    private DominoService service = new DominoService();
    private MarketService marketService = new MarketService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            System.out.println(request.getParameter("ids"));
            Market market = new Market(service.getByIds(request.getParameter("ids")), request.getParameter("chainName"));
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            if(marketService.saveMarketAndAllChains(market)){
                response.getWriter().write("Chain Saved");
            }else{
                response.getWriter().write("Error, name already exists");
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

package controllers;

import listeners.Config;
import services.ChainService;
import services.DominoService;
import services.MarketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by viko0417 on 11/14/2017.
 */
public abstract class MainServlet extends HttpServlet {
    protected Config context;
    protected DominoService dominoService;
    protected MarketService marketService;
    protected ChainService chainService;

    @Override
    public void init() throws ServletException {
        context = Config.getInstance(getServletContext());
        dominoService = context.getDominoService();
        marketService = context.getMarketService();
        chainService = context.getChainService();
    }


}

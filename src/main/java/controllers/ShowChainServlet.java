package controllers;

import entity.Chain;
import entity.Domino;
import services.ChainService;
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
public class ShowChainServlet extends HttpServlet {
    private DominoService service = new DominoService();
    private ChainService chainService = new ChainService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("names", chainService.getAllNames());
        String idString = request.getParameter("ids");
        System.out.println("ids: "+ idString);
        List<Domino> dominoes = service.getByIds(idString);
        request.setAttribute("showDomino", dominoes);
        Chain chain = null;
        try {
            chain = new Chain(dominoes, "");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Set<String> set = new HashSet<String>();
        for (List<Domino> chains : chain.getAllChains()) {
            String s = "";
            for (Domino domino : chains) {
                s += domino.getFirstNum() + ":" + domino.getSecondNum() + " ; ";
            }
            set.add(s);
            System.out.println(s);
        }
        System.out.println(set.size());
        request.setAttribute("chains", chain.getAllChains());
        request.getRequestDispatcher("/pages/showChain.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("names", chainService.getAllNames());
        String idString = request.getParameter("ids");
        System.out.println("ids: "+ idString);
        List<Domino> dominoes = service.getByIds(idString);
        request.setAttribute("showDomino", dominoes);
        Chain chain = null;
        try {
            chain = new Chain(dominoes, "");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Set<String> set = new HashSet<String>();
        for (List<Domino> chains : chain.getLongestChains()) {
            String s = "";
            for (Domino domino : chains) {
                s += domino.getFirstNum() + ":" + domino.getSecondNum() + " ; ";
            }
            set.add(s);
            System.out.println(s);
        }
        System.out.println(set.size());
        request.setAttribute("chains", chain.getLongestChains());
        request.getRequestDispatcher("/pages/showChain.jsp").forward(request,response);
    }
}

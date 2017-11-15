package controllers;

import entity.Domino;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Servlet that handles HTTP requests that come from the url: /get-domino
 * Created by Viacheslav Koshchii on 07.11.2017.
 */
public class ShowServlet extends MainServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Random r = new Random();
        int num = r.nextInt((28 - 2) + 1)+2;
        List<Domino> dominoes = dominoService.getRandom(num);
        context.getJdbcObj().printDbStatus();
        request.setAttribute("showDomino", dominoes);
        StringBuilder idString = new StringBuilder("");
        for(int i=0; i<dominoes.size(); i++){
            if(i==dominoes.size()-1){
                idString.append(dominoes.get(i).getId());
            }else{
                idString.append(dominoes.get(i).getId()).append(",");
            }
        }
        request.setAttribute("ids", idString.toString());
        request.getRequestDispatcher("/pages/showDomino.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(Integer.parseInt(request.getParameter("num"))>28){
            response.sendRedirect("/start-game");
        }
        List<Domino> dominoes = dominoService.getRandom(Integer.parseInt(request.getParameter("num")));
        context.getJdbcObj().printDbStatus();
        request.setAttribute("showDomino", dominoes);
        StringBuilder idString = new StringBuilder("");
        for(int i=0; i<dominoes.size(); i++){
            if(i==dominoes.size()-1){
                idString.append(dominoes.get(i).getId());
            }else{
                idString.append(dominoes.get(i).getId()).append(",");
            }
        }
        request.setAttribute("ids", idString.toString());
        request.getRequestDispatcher("/pages/showDomino.jsp").forward(request,response);
    }
}

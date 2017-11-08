package controllers;

import entity.Domino;
import services.DominoService;
import services.impl.DominoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Servlet that handles HTTP requests that come from the url: /get-domino
 * Created by Viacheslav Koshchii on 07.11.2017.
 */
public class ShowServlet extends HttpServlet {
    private DominoService service = new DominoServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Random r = new Random();
        int num = r.nextInt((28 - 2) + 1)+2;
        List<Domino> dominoes = service.getRandom(num);
        request.setAttribute("showDomino", dominoes);
        String idString = "";
        for(int i=0; i<dominoes.size(); i++){
            if(i==dominoes.size()-1){
                idString +=dominoes.get(i).getId();
            }else{
                idString+=dominoes.get(i).getId()+",";
            }
        }
        request.setAttribute("ids", idString);
        request.getRequestDispatcher("/pages/showDomino.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(Integer.parseInt(request.getParameter("num"))>28){
            response.sendRedirect("/start-game");
        }
        List<Domino> dominoes = service.getRandom(Integer.parseInt(request.getParameter("num")));
        request.setAttribute("showDomino", dominoes);
        String idString = "";
        for(int i=0; i<dominoes.size(); i++){
            if(i==dominoes.size()-1){
                idString +=dominoes.get(i).getId();
            }else{
                idString+=dominoes.get(i).getId()+",";
            }
        }
        request.setAttribute("ids", idString);
        request.getRequestDispatcher("/pages/showDomino.jsp").forward(request,response);
    }

}

package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles HTTP requests that come from the url: /start-game
 * Created by Viacheslav Koshchii on 07.11.2017.
 */
public class DominoServlet extends MainServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("dominoes", dominoService.getAll());
        context.getJdbcObj().printDbStatus();
        request.getRequestDispatcher("/pages/home.jsp").forward(request,response);
    }
}

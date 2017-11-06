package controllers;

import services.ChainService;
import services.DominoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 06.11.2017.
 */
public class ChainNameServlet extends HttpServlet {
    private DominoService service = new DominoService();
    private ChainService chainService = new ChainService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getParameter("chainname"));
        request.getRequestDispatcher("/pages/showChain.jsp").forward(request,response);
    }

}

package controllers;

import services.DominoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DominoServlet extends HttpServlet {
    private DominoService service = new DominoService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("dominoes", service.getAll());
        request.getRequestDispatcher("/pages/home.jsp").forward(request,response);
    }
}

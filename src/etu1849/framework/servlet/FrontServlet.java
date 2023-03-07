package etu1849.framework.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utils.Utilitaire;

public class FrontServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Utilitaire util = new Utilitaire();
        out.println(util.processURL(request.getRequestURI()));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }
}

package etu1849.framework.servlet;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import etu1849.framework.ClassMapping;
import etu1849.framework.utils.Utilitaire;

public class FrontServlet extends HttpServlet{
    HashMap<String,ClassMapping> MappingUrls = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        Utilitaire util = new Utilitaire();
        try {
            String classes = getServletContext().getResource(".").toURI().getPath()+"WEB-INF/classes";
            Vector<Class> allClasses = util.readPackage(classes, "");
            Vector<String[]> info = util.getInfo(allClasses);
            for (int i = 0; i < info.size(); i++) {
                ClassMapping tmp = new ClassMapping(info.get(i)[0], info.get(i)[1]);
                MappingUrls.put(info.get(i)[2], tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Utilitaire util = new Utilitaire();
        out.println(util.processURL(request.getRequestURI()));
        out.print(MappingUrls.get("/emp-all").getClassName()+"----------");
        out.println(MappingUrls.get("/emp-all").getMethod());
        out.print(MappingUrls.get("/emp-add").getClassName()+"----------");
        out.println(MappingUrls.get("/emp-add").getMethod());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }
}

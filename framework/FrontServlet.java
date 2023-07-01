package etu1849.framework.servlet;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import etu1849.framework.ClassMapping;
import etu1849.framework.utils.Utilitaire;
import etu1849.framework.ModelView;

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
        try {
            // Iterator iti = MappingUrls.entrySet().iterator();
            //     while (iti.hasNext()) {
            //         Map.Entry mapentry = (Map.Entry) iti.next();
            //         out.println((String)mapentry.getKey());
            //     }
            String url = util.processURL(request.getRequestURI());
            String newURL = "/".concat(url);                    //nampina / teo alohan'ilay url satria ilay *.do no azony
            //out.print(url);
            if(MappingUrls.containsKey(newURL)){
                out.println("ao");
                ClassMapping mapping = MappingUrls.get(newURL);
                Class classmap = Class.forName(mapping.getClassName());
                Object objet = classmap.getConstructor().newInstance();

                //form-lien------------
                Field[] attributs = classmap.getDeclaredFields();
                for (int i = 0; i < attributs.length; i++) {
                    if(request.getParameter(attributs[i].getName()) != null){
                        attributs[i].setAccessible(true);
                        String valeur = request.getParameter(attributs[i].getName());
                        if(valeur != null){
                            Object converted = util.caster(valeur, attributs[i].getType());
                            attributs[i].set(objet, converted); 
                        }
                    }
                }
                //----------------
                
                //sprint8 misy paramètre ilay fonction
                ModelView model = new ModelView();
                String[] parameters = util.getParameter(request);
                Method[] methods = classmap.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().equals(mapping.getMethod())) {
                        if (method.getParameterTypes().length > 0) {
                            Object[] args = util.castParameter(parameters, method);
                            model = (ModelView) method.invoke(objet, args);
                        } else {
                            model = (ModelView) method.invoke(objet);
                        }
                    }
                }

                //----------------

                //ModelView view = (ModelView) classmap.getDeclaredMethod(mapping.getMethod()).invoke(objet);
                Iterator it = model.getData().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry mapentry = (Map.Entry) it.next();
                    request.setAttribute((String)mapentry.getKey(), mapentry.getValue());
                }

                RequestDispatcher dispatch = request.getRequestDispatcher(model.getUrlView());
                dispatch.forward(request, response);
            } else {
                out.print("tsy ao");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }
}

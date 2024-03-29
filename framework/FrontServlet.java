package etu1849.framework.servlet;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import etu1849.framework.ClassMapping;
import etu1849.framework.utils.Utilitaire;
import etu1849.framework.ModelView;
import etu1849.framework.Upload;
import etu1849.framework.annotation.Scope;
import etu1849.framework.annotation.Auth;


@MultipartConfig(fileSizeThreshold = 1024 * 1024)
public class FrontServlet extends HttpServlet{
    HashMap<String,ClassMapping> MappingUrls = new HashMap<>();
    HashMap<Class, Object> singleton = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        Utilitaire util = new Utilitaire();
        try {
            String classes = getServletContext().getResource(".").toURI().getPath()+"WEB-INF/classes";
            Vector<Class> allClasses = util.readPackage(classes, "");

            for (int i = 0; i < allClasses.size(); i++) {
                if(allClasses.get(i).getAnnotation(Scope.class) != null){
                    if(((Scope) allClasses.get(i).getAnnotation(Scope.class)).value().equalsIgnoreCase("singleton")){
                        this.singleton.put(allClasses.get(i), allClasses.get(i).getConstructor().newInstance());
                        System.out.println(allClasses.get(i).getSimpleName());
                    }
                }
            }

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

            String url = util.processURL(request.getRequestURI());
            String newURL = "/".concat(url);                    //nampina / teo alohan'ilay url satria ilay *.do no azony
            
            HttpSession session = request.getSession();
            boolean autorisation = false;

            if(MappingUrls.containsKey(newURL)){
                //out.println("ao");
                ClassMapping mapping = MappingUrls.get(newURL);
                Class classmap = Class.forName(mapping.getClassName());

                Object objet = null;
                if (this.singleton.containsKey(classmap)) {
                    objet = this.singleton.get(classmap);
                    System.out.println("efa nisy instance");
                } else {
                    objet = classmap.getConstructor().newInstance();
                    System.out.println("instance vaovao");
                }

                //form-lien------------
                Field[] attributs = classmap.getDeclaredFields();

                for (int i = 0; i < attributs.length; i++) {
                    if (attributs[i].getType() == Upload.class) {
                        attributs[i].setAccessible(true);
                        try {
                            Part part = request.getPart(attributs[i].getName());
                            InputStream is = part.getInputStream();
                            String name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                            byte[] file = is.readAllBytes();
                            Upload uploadFile = new Upload(name, file);
                            attributs[i].set(objet, uploadFile);
                        } catch (Exception e) {

                        }
                    }
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

                        Auth authentification = method.getAnnotation(Auth.class);
                        if (authentification == null){
                            autorisation = true;
                        }
                        else{
                            String profil= authentification.profil(); 
                            if(session.getAttribute(getInitParameter("session")) != null){                  //mijery ilay init-param any amin'nyxml
                                if(profil.equals("")) autorisation = true;
                                else if(session.getAttribute(getInitParameter("session")).equals(profil)) autorisation = true;
                            }else{
                                if(profil.equals("")) autorisation = true;
                            }
                        }
                        if (method.getParameterTypes().length > 0) {
                            if (autorisation == false) throw new Exception("You don't have access to this page, please log in");

                            Object[] args = util.castParameter(parameters, method);
                            model = (ModelView) method.invoke(objet, args);

                            for(Entry mapentry : model.getSession().entrySet()){
                                session.setAttribute((String)mapentry.getKey(),mapentry.getValue());
                            }
                        } else {
                            if (autorisation == false) throw new Exception("You don't have access to this page, please log in");

                            model = (ModelView) method.invoke(objet);

                            for(Entry mapentry : model.getSession().entrySet()){
                                session.setAttribute((String)mapentry.getKey(),mapentry.getValue());
                            }
                        }
                    }
                }

                //----------------

                Iterator it = model.getData().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry mapentry = (Map.Entry) it.next();
                    request.setAttribute((String)mapentry.getKey(), mapentry.getValue());
                }

                RequestDispatcher dispatch = request.getRequestDispatcher(model.getUrlView());
                dispatch.forward(request, response);
            } else {
                //out.print("tsy ao");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }

    public HashMap<String, ClassMapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, ClassMapping> mappingUrls) {
        MappingUrls = mappingUrls;
    }

    public HashMap<Class, Object> getSingleton() {
        return singleton;
    }

    public void setSingleton(HashMap<Class, Object> singleton) {
        this.singleton = singleton;
    }
}

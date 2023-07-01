package etu1849.framework.utils;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.lang.reflect.Method;
import java.io.*;
import java.sql.Date;
import java.util.Vector;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

import etu1849.framework.annotation.Urls;

public class Utilitaire {
    
    public String processURL(String url){
        String[] complet = url.split("/");
        Vector<String> necessaire = new Vector<>();
        for (int i = 2; i < complet.length; i++) {
            necessaire.add(complet[i]);
        }
        String valiny = "";
        for (int i = 0; i < necessaire.size(); i++) {
            valiny = valiny.concat(necessaire.get(i));
            if(necessaire.size() != 1) valiny = valiny.concat("/");
        }
        return valiny;
    }

    public Vector<Class> readPackage(String path, String pack) throws Exception{
        File file = new File(path);
        Vector<Class> valiny = new Vector<>();
        Vector<Class> tempo = new Vector<>();
        for (int i = 0; i < file.listFiles().length; i++) {
            if(file.listFiles()[i].isDirectory()){
                if(file.getName().equalsIgnoreCase("classes") == false && pack.contains(file.getName()) == false){
                    pack = pack.concat(file.getName());
                    pack = pack.concat(".");
                }
                tempo = readPackage(file.listFiles()[i].getPath(), pack);
                for (int j = 0; j < tempo.size(); j++) {
                    valiny.add(tempo.get(j));
                }
            } else {
                if(file.getName().equalsIgnoreCase("classes") == false){
                    pack = pack.concat(file.getName());
                    pack = pack.concat(".");
                }
                Class temp = Class.forName(pack+file.listFiles()[i].getName().split("[.]")[0]);
                valiny.add(temp);
            }
        }
        return valiny;
    }

    public Vector<String[]> getInfo(Vector<Class> classes){
        Vector<String[]> valiny = new Vector<>();
        for (int i = 0; i < classes.size(); i++) {
            for (int j = 0; j < classes.get(i).getDeclaredMethods().length; j++) {
                if(classes.get(i).getDeclaredMethods()[j].getAnnotation(Urls.class) != null){
                    String[] temp = new String[3];
                    temp[0] = classes.get(i).getName();
                    temp[1] = classes.get(i).getDeclaredMethods()[j].getName();
                    temp[2] = classes.get(i).getDeclaredMethods()[j].getAnnotation(Urls.class).url();
                    valiny.add(temp);
                }
            }
        }

        return valiny;
    }

    public static <T> T caster(String val, Class<T> type){
        if(type == Date.class){
            Date valiny = Date.valueOf(val);
            return (T) valiny;
        }
        PropertyEditorSupport editor = (PropertyEditorSupport) PropertyEditorManager.findEditor(type);
        editor.setAsText(val);
        return (T) editor.getValue();
    }

    public static String[] getParameter(HttpServletRequest req){
        Enumeration<String> parameterName = req.getParameterNames();
        Vector<String> allName = new Vector<>();
        while (parameterName.hasMoreElements()) {
            allName.add(parameterName.nextElement());
        }

        String[] valiny = new String[allName.size()];
        for (int i = 0; i < valiny.length; i++) {
            valiny[i] = req.getParameter(allName.get(i));
        }
        return valiny;
    }

    public static Object[] castParameter(String[] param, Method meth){
        Object[] valiny = new Object[param.length];
        for (int i = 0; i < valiny.length; i++) {
            valiny[i] = caster(param[i], meth.getParameterTypes()[i]);
        }
        return valiny;
    }
}

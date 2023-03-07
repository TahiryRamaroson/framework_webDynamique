package utils;

import java.util.Vector;

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
            valiny = valiny.concat("/");
        }
        return valiny;
    }
}

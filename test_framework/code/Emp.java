package modele;

import etu1849.framework.annotation.Urls;
import etu1849.framework.ModelView;

public class Emp {
    String idEmp;
    String nom;

    @Urls( url = "/emp-all")
    public void findAll(){
        System.out.println("findall");
    }

    @Urls( url = "/emp-add")
    public void addEmp(){
        System.out.println("addEmp");
    }

    @Urls( url = "Home")
    public ModelView getHome(){
        return new ModelView("Home.jsp");
    }

    public String getIdEmp() {
        return idEmp;
    }
    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}

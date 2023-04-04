package modele;

import etu1849.framework.annotation.Urls;

import java.util.Vector;
import java.util.ArrayList;

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
    public ModelView getAll(){
        ModelView view = new ModelView("Home.jsp");
        Emp un = new Emp();
        un.setIdEmp("Emp1");
        un.setNom("Bema");
        Emp deux = new Emp();
        deux.setIdEmp("Emp2");
        deux.setNom("Fara");
        ArrayList<Emp> allEmp = new ArrayList<>();
        allEmp.add(un);
        allEmp.add(deux);
        view.addItem("lst", allEmp);
        return view;
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

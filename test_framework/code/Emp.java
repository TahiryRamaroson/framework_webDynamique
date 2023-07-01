package modele;

import etu1849.framework.annotation.Urls;

import java.util.Vector;
import java.sql.Date;
import java.util.ArrayList;

import etu1849.framework.ModelView;

public class Emp {
    String idEmp;
    String nom;
    int age;
    Date naissance;

    public Emp(){}

    public Emp(String idEmp, String nom, int age, Date daty) {
        this.idEmp = idEmp;
        this.nom = nom;
        this.age = age;
        this.naissance = daty;
    }

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
        un.setAge(17);
        Emp deux = new Emp();
        deux.setIdEmp("Emp2");
        deux.setNom("Fara");
        deux.setAge(25);
        ArrayList<Emp> allEmp = new ArrayList<>();
        allEmp.add(un);
        allEmp.add(deux);
        view.addItem("lst", allEmp);
        return view;
    }

    @Urls( url = "Formulaire")
    public ModelView getForm(){
        ModelView view = new ModelView("Formulaire.jsp");
        return view;
    }

    @Urls( url = "Traitement")
    public ModelView traitement(){
        ModelView view = new ModelView("Traitement.jsp");
        Emp temp = new Emp("emp1", this.nom, this.age, this.naissance);
        view.addItem("Empl", temp);
        return view;
    }

    @Urls( url = "Recherche")
    public ModelView getSearch(){
        ModelView view = new ModelView("Recherche.jsp");
        return view;
    }

    @Urls( url = "Search_name")
    public ModelView getSearchName(String name){
        ModelView view = new ModelView("ResultatRecherche.jsp");
        Emp[] emp = new Emp[3];
        emp[0] = new Emp("id1", "Bema", 15, Date.valueOf("2008-05-14"));
        emp[1] = new Emp("id2", "Soa", 20, Date.valueOf("2003-11-23"));
        emp[2] = new Emp("id3", "Lita", 25, Date.valueOf("1998-06-02"));
        ArrayList<Emp> result = new ArrayList<>();
         for (int i = 0; i < emp.length; i++) {
            if(name.equalsIgnoreCase(emp[i].getNom())){
                result.add(emp[i]);
            }
         }

        view.addItem("lst", result);
        return view;
    }

    @Urls( url = "Search_age")
    public ModelView getSearchAge(int age){
        ModelView view = new ModelView("ResultatRecherche.jsp");
        Emp[] emp = new Emp[3];
        emp[0] = new Emp("id1", "Bema", 15, Date.valueOf("2008-05-14"));
        emp[1] = new Emp("id2", "Soa", 20, Date.valueOf("2003-11-23"));
        emp[2] = new Emp("id3", "Lita", 25, Date.valueOf("1998-06-02"));
        ArrayList<Emp> result = new ArrayList<>();
         for (int i = 0; i < emp.length; i++) {
            if(age == emp[i].getAge()){
                result.add(emp[i]);
            }
         }

        view.addItem("lst", result);
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getNaissance() {
        return naissance;
    }
    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }
}

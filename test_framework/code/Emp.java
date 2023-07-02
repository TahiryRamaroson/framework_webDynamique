package modele;

import etu1849.framework.annotation.Urls;
import etu1849.framework.annotation.Scope;

import java.util.Vector;
import java.sql.Date;
import java.util.ArrayList;

import etu1849.framework.ModelView;
import etu1849.framework.Upload;

@Scope("singleton")
public class Emp {
    String idEmp;
    String nom;
    int age;
    Date naissance;
    Upload badge;


    public Emp(){}

    public Emp(String idEmp, String nom, int age, Date daty) {
        this.idEmp = idEmp;
        this.nom = nom;
        this.age = age;
        this.naissance = daty;
    }

    public Emp(String idEmp, String nom, int age, Date daty, Upload badge) {
        this.idEmp = idEmp;
        this.nom = nom;
        this.age = age;
        this.naissance = daty;
        this.badge = badge;
    }

    @Urls( url = "/emp-all")
    public void findAll(){
        System.out.println("findall");
    }

    @Urls( url = "/emp-add")
    public void addEmp(){
        System.out.println("addEmp");
    }

    @Urls( url = "/Home.do")
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

    @Urls( url = "/Formulaire.do")
    public ModelView getForm(){
        ModelView view = new ModelView("Formulaire.jsp");
        return view;
    }

    @Urls( url = "/Traitement.do")
    public ModelView traitement(){
        ModelView view = new ModelView("Traitement.jsp");
        Emp temp = new Emp("emp1", this.nom, this.age, this.naissance);
        view.addItem("Empl", temp);
        return view;
    }

    @Urls( url = "/Recherche.do")
    public ModelView getSearch(){
        ModelView view = new ModelView("Recherche.jsp");
        return view;
    }

    @Urls( url = "/Search_name.do")
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

    @Urls( url = "/Search_age.do")
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

    @Urls( url = "/FormUpload.do")
    public ModelView getFormUpload(){
        ModelView view = new ModelView("FormulaireUpload.jsp");
        return view;
    }

    @Urls( url = "/Upload.do")
    public ModelView traitementUpload(){
        ModelView view = new ModelView("ResultatUpload.jsp");
        String nomFichier = this.badge.getName();
        view.addItem("upload", nomFichier);
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
    }public Upload getBadge() {
        return badge;
    }
    public void setBadge(Upload badge) {
        this.badge = badge;
    }
}

package modele;

import annotation.Urls;

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

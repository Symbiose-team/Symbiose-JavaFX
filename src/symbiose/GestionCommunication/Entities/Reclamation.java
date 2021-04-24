/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Entities;



public class Reclamation {

    int id;
    String desc;
    String etat;
    String sujet;
    User user;
    String Date;

    public Reclamation(int id, String desc, String etat, String sujet, User user, String Date) {
        this.id = id;
        this.desc = desc;
        this.etat = etat;
        this.sujet = sujet;
        this.user = user;
        this.Date = Date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Reclamation() {
        this.etat =etat;
    }


    public Reclamation(int id,String sujet, String desc, String etat, User user) {
        this.id = id;
        this.sujet=sujet;
        this.desc = desc;
        this.etat = etat;
        this.user = user;
    }

   
    public int getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getSujet() {
        return sujet;
    }
    
        String first_name;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Reclamation(int id, String sujet,String desc, String etat, User user, String Date, String first_name) {
        this.id = id;
        this.desc = desc;
        this.etat = etat;
        this.sujet = sujet;
        this.user = user;
        this.Date = Date;
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", desc=" + desc + ", etat=" + etat + ", sujet=" + sujet + ", user=" + user + ", Date=" + Date + ", first_name=" + first_name + '}';
    }



    

}

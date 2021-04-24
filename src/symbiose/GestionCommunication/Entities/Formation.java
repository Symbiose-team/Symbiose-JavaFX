/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Entities;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Hazem
 */


public class Formation {
        int id;
    String categ_formation;
    String nom_formation;
    int prix_formation;
    String duree_formation; 
    int valide; 
    String desc_formation;
    LocalDateTime date;
    
    public Formation() {
    }
  public Formation(String categ_formation, String nom_formation, int prix_formation, String duree_formation, int valide, String desc_formation) {
        this.categ_formation = categ_formation;
        this.nom_formation = nom_formation;
        this.prix_formation = prix_formation;
        this.duree_formation = duree_formation;
        this.valide = valide;
        this.desc_formation = desc_formation;
        this.date=date;
    }
    public Formation(int id, String categ_formation, String nom_formation, int prix_formation, String duree_formation, int valide, String desc_formation) {
        this.id = id;
        this.categ_formation = categ_formation;
        this.nom_formation = nom_formation;
        this.prix_formation = prix_formation;
        this.duree_formation = duree_formation;
        this.valide = valide;
        this.desc_formation = desc_formation;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", categ_formation=" + categ_formation + ", nom_formation=" + nom_formation + ", prix_formation=" + prix_formation + ", duree_formation=" + duree_formation + ", valide=" + valide + ", desc_formation=" + desc_formation +", date=" + date + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCateg_formation(String categ_formation) {
        this.categ_formation = categ_formation;
    }

    public void setNom_formation(String nom_formation) {
        this.nom_formation = nom_formation;
    }

    public void setPrix_formation(int prix_formation) {
        this.prix_formation = prix_formation;
    }

    public void setDuree_formation(String duree_formation) {
        this.duree_formation = duree_formation;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public void setDesc_formation(String desc_formation) {
        this.desc_formation = desc_formation;
    }

    public int getId() {
        return id;
    }

    public String getCateg_formation() {
        return categ_formation;
    }

    public String getNom_formation() {
        return nom_formation;
    }

    public int getPrix_formation() {
        return prix_formation;
    }

    public String getDuree_formation() {
        return duree_formation;
    }

    public int getValide() {
        return valide;
    }

    public String getDesc_formation() {
        return desc_formation;
    }
    
  

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime now) {
                this.date = date;

    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Entities;

import java.sql.Date;

/**
 *
 * @author chaima
 */
public class User {

    

    private int id=9;
    private String firstname="Elliott";
    private String lastname="Bins";
    private String email="";
    private String role="joueur";

   
    private String picture="elliot.png";


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 public User() {
     
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
     public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
     public User(int id, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
   @Override
    public String toString() {
        return  firstname ;
    }
}

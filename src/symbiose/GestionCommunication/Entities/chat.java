/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Entities;

/**
 *
 * @author chaima
 */
public class chat {
    int id;
    int iduser1;
    int iduser2;
    String name;

    public chat(int id, int iduser1, int iduser2, String name) {
        this.id = id;
        this.iduser1 = iduser1;
        this.iduser2 = iduser2;
        this.name = name;
    }

    public chat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser1() {
        return iduser1;
    }

    public void setIduser1(int iduser1) {
        this.iduser1 = iduser1;
    }

    public int getIduser2() {
        return iduser2;
    }

    public void setIduser2(int iduser2) {
        this.iduser2 = iduser2;
    }

    @Override
    public String toString() {
        return  id +"\n"+ name ;
    }
    
}
